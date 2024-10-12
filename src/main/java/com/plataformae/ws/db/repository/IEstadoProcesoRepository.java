package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.EstadoProceso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoProcesoRepository  extends JpaRepository<EstadoProceso,Integer> {
}
