package com.plataformae.ws.db.repository.jpa;

import com.plataformae.ws.db.entity.Universidad;
import com.plataformae.ws.dto.CarreraUniversidadResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IUniversidadRepository extends JpaRepository<Universidad, Integer>, JpaSpecificationExecutor<Universidad> {

    @Transactional
    public List<Universidad> findAllByEstado(String estado);

    public List<Universidad> findByNit(String nit);

}
