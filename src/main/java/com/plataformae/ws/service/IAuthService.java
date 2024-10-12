package com.plataformae.ws.service;

import com.plataformae.ws.dto.AuthRequestDTO;
import com.plataformae.ws.dto.AuthResponseDTO;

public interface IAuthService {

    void authenticateUser(AuthRequestDTO authRequestDTO);

    AuthResponseDTO createAuthResponse(AuthRequestDTO authRequestDTO);

    String generarSesionRegistro(AuthRequestDTO authRequestDTO);
}
