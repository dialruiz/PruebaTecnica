package com.seguros.apirest.Repository;

import com.seguros.apirest.entities.Seguros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SegurosRepository  extends JpaRepository<Seguros, Long>, JpaSpecificationExecutor<Seguros> {

    @Query("SELECT s FROM Seguros s "
            + "WHERE s.asegurado.tipoIdentificacion.id = :tipoIdentificacion AND s.asegurado.numeroIdentificacion = :numeroIdentificacion" )
    List<Seguros> buscarSegurosPorAsegurado(@Param("tipoIdentificacion") Long tipoIdentificacion, @Param("numeroIdentificacion") String numeroIdentificacion);


}
