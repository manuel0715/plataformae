package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarreraJpaRepository extends JpaRepository<Carrera, Long> {
    List<Carrera> findByNombreContainingIgnoreCase(String nombre);

}
