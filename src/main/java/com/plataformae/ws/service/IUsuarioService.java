package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.dto.ApiResponsePageDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsuarioService {
    public boolean existeUsuario(String identificacion, String tipoDocumento);

    public boolean existeCelular(String identificacion);

    public Usuarios crearUsuario(Usuarios usuario);

    public boolean existeEmail(String email,String identificacion,String tipoIdentificacion);

    public Usuarios buscarUsuario(Usuarios usuarios);

    public int updatePasword(String username, String password);

    public boolean existeUsuario(String usuario);

    Usuarios actualizarPerfil(String authenticatedUser, Usuarios request);

    Usuarios cargarInformacionPerfil(String authenticatedUser);

    ResponseEntity<ApiResponsePageDTO<List<Usuarios>>> cargarUsuarios(int page, int size);
}
