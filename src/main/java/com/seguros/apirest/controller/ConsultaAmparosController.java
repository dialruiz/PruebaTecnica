package com.seguros.apirest.controller;

import com.seguros.apirest.dto.ConsultarAmparosPorUsuariosInDTO;
import com.seguros.apirest.dto.ResultadoConsultaAseguradoDTO;
import com.seguros.apirest.exception.ValidacionException;
import com.seguros.apirest.service.ConsultaAmparosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api")
public class ConsultaAmparosController {

    @Autowired
    private ConsultaAmparosService consultaAmparosService;

    @PostMapping("/consultarAmparosPorPersona")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarAmparosPorPersona(@Valid @RequestBody ConsultarAmparosPorUsuariosInDTO consultarAmparosPorUsuariosInDTO){

        try{
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(consultaAmparosService.consultarAmparosPorPersona(consultarAmparosPorUsuariosInDTO));
        }catch(ValidacionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
