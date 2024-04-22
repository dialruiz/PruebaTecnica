package com.seguros.apirest.Repository;

import com.seguros.apirest.entities.Primas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrimasRepository extends JpaRepository<Primas, Long>, JpaSpecificationExecutor<Primas> {

    List<Primas> findByAmparoIdAndEdadMinimaLessThanEqualAndEdadMaximaGreaterThanEqual(Long amparoId, int edad1, int edad2);
}
