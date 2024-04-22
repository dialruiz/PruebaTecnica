package com.seguros.apirest.service;


import com.seguros.apirest.Repository.AseguradosRepository;
import com.seguros.apirest.Repository.PrimasRepository;
import com.seguros.apirest.Repository.SegurosRepository;
import com.seguros.apirest.Repository.TiposIdentificacionRepository;
import com.seguros.apirest.dto.ConsultarAmparosPorUsuariosInDTO;
import com.seguros.apirest.dto.LiquidacionDTO;
import com.seguros.apirest.dto.ResultadoConsultaAseguradoDTO;
import com.seguros.apirest.entities.Asegurados;
import com.seguros.apirest.entities.Primas;
import com.seguros.apirest.entities.Seguros;
import com.seguros.apirest.entities.TiposIdentificacion;
import com.seguros.apirest.exception.ValidacionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsultaAmparosService {

    @Autowired
    private AseguradosRepository aseguradosRepository;

    @Autowired
    private SegurosRepository segurosRepository;

    @Autowired
    private PrimasRepository primasRepository;

    @Autowired
    TiposIdentificacionRepository tiposIdentificacionRepository;

    public ResultadoConsultaAseguradoDTO consultarAmparosPorPersona(ConsultarAmparosPorUsuariosInDTO consultarAmparosPorUsuariosInDTO) throws ValidacionException {

        validarCampos(consultarAmparosPorUsuariosInDTO);

        List<Asegurados> asegurado = aseguradosRepository.findAll();

        List<Seguros> listaSeguros = segurosRepository.buscarSegurosPorAsegurado(consultarAmparosPorUsuariosInDTO.getIdTipoIdentificacion(),
                                                                                 consultarAmparosPorUsuariosInDTO.getNumeroIdentificacion());

        if(listaSeguros.isEmpty()) {
            ResultadoConsultaAseguradoDTO resultado = new ResultadoConsultaAseguradoDTO();
            TiposIdentificacion tiposIdentificacion = tiposIdentificacionRepository.findTiposIdentificacionById(consultarAmparosPorUsuariosInDTO.getIdTipoIdentificacion());
            resultado.setTipoIdentificacion(tiposIdentificacion.getDescripcion());
            resultado.setNumeroIdentificacion(consultarAmparosPorUsuariosInDTO.getNumeroIdentificacion());

            return resultado;
        }

        int edadAsegurado = calcularEdadAsegurado(listaSeguros.get(0).getAsegurado().getFechaNacimiento());

        ResultadoConsultaAseguradoDTO resultado = new ResultadoConsultaAseguradoDTO();

        resultado.setTipoIdentificacion(listaSeguros.get(0).getAsegurado().getTipoIdentificacion().getDescripcion());
        resultado.setNumeroIdentificacion(listaSeguros.get(0).getAsegurado().getNumeroIdentificacion());
        resultado.setValorAsegurado(consultarAmparosPorUsuariosInDTO.getValorAsegurado());

        List<LiquidacionDTO> listaLiquidacion = new ArrayList<>();

        for(Seguros seguro : listaSeguros ) {

            List<Primas> primas = primasRepository.findByAmparoIdAndEdadMinimaLessThanEqualAndEdadMaximaGreaterThanEqual(seguro.getAmparo().getId(),edadAsegurado,edadAsegurado);

            if(!primas.isEmpty()) {
                LiquidacionDTO liquidacionDTO = new LiquidacionDTO();

                liquidacionDTO.setCodigoAmparo(seguro.getAmparo().getId());
                liquidacionDTO.setNombreAmparo(seguro.getAmparo().getNombre());
                liquidacionDTO.setValorPrima(consultarAmparosPorUsuariosInDTO.getValorAsegurado().multiply(primas.get(0).getPorcentajePrima()));
                listaLiquidacion.add(liquidacionDTO);

            }


        }

        BigDecimal valorTotal = listaLiquidacion.stream()
                .map(LiquidacionDTO::getValorPrima)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        resultado.setValorTotal(valorTotal);
        resultado.setListaLiquidacion(listaLiquidacion);

        return resultado;
    }

    public int calcularEdadAsegurado(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechaNacimiento, fechaActual);

        return periodo.getYears();
    }

    private void validarCampos(ConsultarAmparosPorUsuariosInDTO consultarAmparosPorUsuariosInDTO) throws ValidacionException {

        String mensajeError = "";

        if(consultarAmparosPorUsuariosInDTO.getIdTipoIdentificacion() == null){
            mensajeError += "El campo tipo identificacion es obligatorio, ";
        }

        if(consultarAmparosPorUsuariosInDTO.getNumeroIdentificacion() == null){
            mensajeError += "El campo tipo numero identificacion es obligatorio, ";
        }

        if(consultarAmparosPorUsuariosInDTO.getValorAsegurado() == null){
            mensajeError += "El campo tipo valor asegurado es obligatorio ";
        }

        if(consultarAmparosPorUsuariosInDTO.getValorAsegurado() != null && consultarAmparosPorUsuariosInDTO.getValorAsegurado().compareTo(BigDecimal.ZERO) == 0 ){

            mensajeError +=  !mensajeError.isEmpty() &&  !mensajeError.endsWith(", ")? ", " : "";

            mensajeError += "El campo tipo valor asegurado debe ser mayor a cero ";
        }

        if (mensajeError != null && !mensajeError.isEmpty()) {
            throw new ValidacionException(mensajeError);
        }
    }
}
