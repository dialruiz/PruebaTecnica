package com.seguros.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoConsultaAseguradoDTO {

    private String tipoIdentificacion;

    private String numeroIdentificacion;

    private BigDecimal valorAsegurado;

    List<LiquidacionDTO> listaLiquidacion;

    private BigDecimal valorTotal;
}
