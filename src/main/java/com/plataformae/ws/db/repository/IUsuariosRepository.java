package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuariosRepository extends JpaRepository<Usuarios, String> {
    Optional<Usuarios> findByUsername(String username);

    boolean existsByIdentificacionAndTipoIdentificacion(String identificacion, String tipoDocumento);

    boolean existsByCelular(String identificacion);
}