package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.EstadoContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoContactoRepository  extends JpaRepository<EstadoContacto, Integer> {
}
