package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ISedeRepository extends JpaRepository<Sede, Integer>, JpaSpecificationExecutor<Sede> {


}
