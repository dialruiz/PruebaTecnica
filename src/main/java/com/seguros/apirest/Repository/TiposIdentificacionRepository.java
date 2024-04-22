package com.seguros.apirest.Repository;

import com.seguros.apirest.entities.TiposIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TiposIdentificacionRepository extends JpaRepository<TiposIdentificacion, Long>, JpaSpecificationExecutor<TiposIdentificacion> {

    TiposIdentificacion findTiposIdentificacionById(Long id);
}
