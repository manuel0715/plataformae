package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IUsuariosRepository extends JpaRepository<Usuarios, String> {
    Usuarios findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByIdentificacionAndTipoIdentificacion(String identificacion, String tipoDocumento);

    boolean existsByCelular(String identificacion);

    boolean existsByEmailAndIdentificacionAndTipoIdentificacion(String email, String identificacion, String tipoIdentificacion);

    Usuarios findUsuariosByIdentificacionAndTipoIdentificacion(String identificacion,String tipoIdentificacion);

    @Modifying
    @Transactional
    @Query("UPDATE Usuarios u SET u.password = :password WHERE u.username = :username")
    int updatePasword(String username, String password);
}