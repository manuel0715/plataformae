package com.plataformae.ws.service;

import com.plataformae.ws.db.entity.Usuarios;

public interface IUsuarioService {
    public boolean existeUsuario(String identificacion, String tipoDocumento);

    public boolean existeCelular(String identificacion);

    public Usuarios crearUsuario(Usuarios usuario);

}
