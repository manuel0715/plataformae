package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICiudadRepository extends JpaRepository <Ciudad, Integer> {
}
