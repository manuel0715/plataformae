package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


public interface IUsuariosRepository extends JpaRepository<Usuarios, String> {
    Optional<Usuarios> findByUsername(String username);

    Usuarios findByIdentificacion(String username);

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
            "u.departamentoExpedicion.id = :departamentoExpedicion, " +
            "u.municipioExpedicion.id = :municipioExpedicion, " +
            "u.fechaNacimiento = :fechaNacimiento, " +
            "u.departamentoNacimiento.id = :departamentoNacimiento, " +
            "u.municipioNacimiento.id = :municipioNacimiento, " +
            "u.email = :email, " +
            "u.celular = :celular, " +
            "u.fechaUltimaModificacion = CURRENT_TIMESTAMP, " +
            "u.usuarioUltimaModificacion = :username " +
            "WHERE u.identificacion = :username")
    int updatePerfilUsuario(String primerNombre,
                            String segundoNombre,
                            String primerApellido,
                            String segundoApellido,
                            String nombreCompleto,
                            int departamentoExpedicion,
                            int municipioExpedicion,
                            LocalDate fechaNacimiento,
                            int departamentoNacimiento,
                            int municipioNacimiento,
                            String email,
                            String celular,
                            String username);

    Page<Usuarios> findByTipoUsuario(String tipoUsuario, Pageable pageable);

}