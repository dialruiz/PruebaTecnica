package com.seguros.apirest.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultarAmparosPorUsuariosInDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El campo idTipoIdentificacion es obligatorio")
    private Long idTipoIdentificacion;

    @NotNull(message = "El campo numeroIdentificacion es obligatorio")
    private String numeroIdentificacion;

    @NotNull(message = "El campo valorAsegurado es obligatorio")
    private BigDecimal valorAsegurado;

}
