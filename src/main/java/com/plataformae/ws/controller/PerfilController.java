package com.plataformae.ws.controller;

import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.RestaurarContrasenaRequestDTO;
import com.plataformae.ws.service.IUsuarioService;
import com.plataformae.ws.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.plataformae.ws.util.Utils.buildResponse;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    private final IUsuarioService usuarioService;
    private final AuthUtil authUtil;

    public PerfilController(IUsuarioService usuarioService, AuthUtil authUtil) {
        this.usuarioService = usuarioService;
        this.authUtil = authUtil;
    }

    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<ApiResponseDTO<String>> actualizarContrasena(@RequestBody RestaurarContrasenaRequestDTO request) {

        usuarioService.updatePasword(authUtil.getAuthenticatedUser(),request.getNuevaContrasena());

        return buildResponse(
                "La contraseña se cambió exitosamente.",
                null,
                HttpStatus.OK
        );
    }
}
