package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.domain.OtpService;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.AuthRequestDTO;
import com.plataformae.ws.dto.AuthResponseDTO;
import com.plataformae.ws.dto.RestaurarContrasenaRequestDTO;
import com.plataformae.ws.service.IAuthService;
import com.plataformae.ws.service.IUsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import static com.plataformae.ws.util.Utils.buildResponse;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    private final MessageConfig messageProperties;
    private final IAuthService iAuthService;
    private final IUsuarioService iUsuarioService;
    private final OtpService otpService;

    @Autowired
    public AuthController(MessageConfig messageProperties, IAuthService iAuthService, IUsuarioService iUsuarioService, OtpService otpService) {
        this.messageProperties = messageProperties;
        this.iAuthService = iAuthService;
        this.iUsuarioService = iUsuarioService;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        LOGGER.info("REQUEST /api/auth/login: {}", authRequestDTO.getUsername());

        Usuarios user =iUsuarioService.cargarInformacionPerfil(authRequestDTO.getUsername());

        if (!user.getTipoUsuario().equals("A") && !authRequestDTO.getTipoUsuario().equals(user.getTipoUsuario())) {
            return buildResponse(
                    "No tienes permisos suficientes para acceder a esta opción",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }


        try {
            if(!iUsuarioService.existeUsuario(authRequestDTO.getUsername())){
                return buildResponse(
                        "Usuario o contraseña invalidos",
                        null,
                        HttpStatus.BAD_REQUEST
                );
            }

            iAuthService.authenticateUser(authRequestDTO);
            AuthResponseDTO authResponseDTO = iAuthService.createAuthResponse(authRequestDTO);
            return buildResponse(messageProperties.messageProperties().getLoginExitoso(), authResponseDTO, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return buildResponse(messageProperties.messageProperties().getCredencialesInvalidas(), null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            LOGGER.error("Error login: {}", ex.getMessage(), ex);
            return buildResponse(messageProperties.messageProperties().getMensajeError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/restaurar")
    public ResponseEntity<ApiResponseDTO<String>>restaurarContrasena(@RequestBody RestaurarContrasenaRequestDTO request) {

        if(!iUsuarioService.existeUsuario(request.getIdentificacion(),request.getTipoIdentificacion())){
            return buildResponse(
                    "El numero documento no se encuentra registrado",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        if(!otpService.validateOtp(request.getOtp())){
            return buildResponse(
                    "El código OTP es inválido o ha expirado. Por favor, intenta generar uno nuevo.",
                    null,
                    HttpStatus.BAD_REQUEST
            );

        }

        String userName= request.getTipoIdentificacion()+request.getIdentificacion();
        iUsuarioService.updatePasword(userName,request.getNuevaContrasena());

        return buildResponse(
                "La contraseña se cambió exitosamente.",
                null,
                HttpStatus.OK
        );
    }

}