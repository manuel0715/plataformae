package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.dto.ApiResponsePageDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsuarioService {
     boolean existeUsuario(String identificacion, String tipoDocumento);

    boolean existeCelular(String identificacion);

    Usuarios crearUsuario(Usuarios usuario);

    boolean existeEmail(String email,String identificacion,String tipoIdentificacion);

    Usuarios buscarUsuario(Usuarios usuarios);

    int updatePasword(String username, String password);

    boolean existeUsuario(String usuario);

    Usuarios actualizarPerfil(String authenticatedUser, Usuarios request);

    Usuarios cargarInformacionPerfil(String authenticatedUser);

    ResponseEntity<ApiResponsePageDTO<List<Usuarios>>> cargarUsuarios(int page, int size,String tipoUsuario);
}
