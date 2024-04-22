package com.seguros.apirest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ASEGURADOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asegurados {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_de_identificacion", nullable = false)
    private TiposIdentificacion tipoIdentificacion;

    @Column(name = "numero_de_identificacion", nullable = false)
    private String numeroIdentificacion;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @ManyToOne
    @JoinColumn(name = "id_sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "fecha_de_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;



}
