package com.plataformae.ws.db.repository.jpa;

import com.plataformae.ws.db.entity.EstadoContacto;
import com.plataformae.ws.db.entity.EstadoProceso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEstadoProcesoRepository  extends JpaRepository<EstadoProceso,Integer> {

    List<EstadoProceso> findByEstadoContacto(Optional<EstadoContacto> estadoContacto);
}
