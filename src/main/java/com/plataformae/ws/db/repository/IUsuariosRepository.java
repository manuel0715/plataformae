package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuariosRepository extends JpaRepository<Usuarios, String> {
    Usuarios findByUsername(String username);

    boolean existsByIdentificacionAndTipoIdentificacion(String identificacion, String tipoDocumento);

    boolean existsByCelular(String identificacion);


}