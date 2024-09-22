package com.plataformae.ws.service;

import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.AuthResponse;

public interface IAuthService {

    void authenticateUser(AuthRequest authRequest);

    AuthResponse createAuthResponse(AuthRequest authRequest);

    String generarSesionRegistro(AuthRequest authRequest);
}
