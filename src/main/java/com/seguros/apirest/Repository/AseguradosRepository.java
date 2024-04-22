package com.seguros.apirest.Repository;

import com.seguros.apirest.entities.Asegurados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AseguradosRepository
    extends JpaRepository<Asegurados, Long>, JpaSpecificationExecutor<Asegurados> {
    List<Asegurados> findAll();
}
