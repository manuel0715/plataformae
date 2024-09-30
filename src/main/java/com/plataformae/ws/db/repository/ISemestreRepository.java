package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISemestreRepository extends JpaRepository<Semestre, Integer> {
}
