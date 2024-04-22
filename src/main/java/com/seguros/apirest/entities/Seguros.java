package com.seguros.apirest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEGUROS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seguros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_asegurado", nullable = false)
    private Asegurados asegurado;

    @ManyToOne
    @JoinColumn(name = "id_amparo", nullable = false)
    private Amparos amparo;


}
