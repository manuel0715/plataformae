package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IUniversidadRepository extends JpaRepository<Universidad, Integer>, JpaSpecificationExecutor<Universidad> {

    @Transactional
    public List<Universidad> findAllByEstado(String estado);

}
