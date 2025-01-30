package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface IUniversidadRepository extends JpaRepository<Universidad, Long>, JpaSpecificationExecutor<Universidad> {

    @Transactional
    List<Universidad> findAllByEstado(String estado);

    List<Universidad> findByNit(String nit);

    @Query("SELECT u FROM Universidad u WHERE u.nit = :nit")
    Optional<Universidad> validateNit(@Param("nit") String nit);

    List<Universidad> findAllByNitAndEstado(String nit,String estado);

    boolean existsByNit(String nit);

}
