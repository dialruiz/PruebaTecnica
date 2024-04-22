package com.seguros.apirest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "PRIMAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Primas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_amparo", nullable = false)
    private Amparos amparo;

    @Column(name = "edad_minima", nullable = false)
    private int  edadMinima;

    @Column(name = "edad_maxima", nullable = false)
    private int edadMaxima;

    @Column(name = "porcentaje_prima", nullable = false)
    private BigDecimal porcentajePrima;

}
