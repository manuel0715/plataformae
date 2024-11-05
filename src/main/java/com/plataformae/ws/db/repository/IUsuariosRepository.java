package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


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

    @Modifying
    @Transactional
    @Query("UPDATE Usuarios u SET u.primerNombre = :primerNombre, " +
            "u.segundoNombre = :segundoNombre, " +
            "u.primerApellido = :primerApellido, " +
            "u.segundoApellido = :segundoApellido, " +
            "u.nombreCompleto = :nombreCompleto, " +
            "u.fechaExpedicionDocumento = :fechaExpedicionDocumento, " +
            "u.fechaVencimientoDocumento = :fechaVencimientoDocumento, " +
            "u.departamentoExpedicion.id = :departamentoExpedicion, " +
            "u.municipioExpedicion.id = :municipioExpedicion, " +
            "u.fechaNacimiento = :fechaNacimiento, " +
            "u.departamentoNacimiento.id = :departamentoNacimiento, " +
            "u.municipioNacimiento.id = :municipioNacimiento, " +
            "u.email = :email, " +
            "u.celular = :celular, " +
            "u.fechaUltimaModificacion = CURRENT_TIMESTAMP, " +
            "u.usuarioUltimaModificacion = :username " +
            "WHERE u.username = :username")
    int updatePerfilUsuario(String primerNombre,
                            String segundoNombre,
                            String primerApellido,
                            String segundoApellido,
                            String nombreCompleto,
                            LocalDate fechaExpedicionDocumento,
                            LocalDate fechaVencimientoDocumento,
                            int departamentoExpedicion,
                            int municipioExpedicion,
                            LocalDate fechaNacimiento,
                            int departamentoNacimiento,
                            int municipioNacimiento,
                            String email,
                            String celular,
                            String username);

}