package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.domain.OtpService;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.AuthResponse;
import com.plataformae.ws.dto.RestaurarContrasenaRequest;
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
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        LOGGER.info("REQUEST /api/auth/login: {}", authRequest.getUsername());

        try {
            if(!iUsuarioService.existeUsuario(authRequest.getUsername())){
                return buildResponse(
                        "Usuario o contraseña invalidos",
                        null,
                        HttpStatus.BAD_REQUEST
                );
            }

            iAuthService.authenticateUser(authRequest);
            AuthResponse authResponse = iAuthService.createAuthResponse(authRequest);
            return buildResponse(messageProperties.messageProperties().getLoginExitoso(), authResponse, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return buildResponse(messageProperties.messageProperties().getCredencialesInvalidas(), null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            LOGGER.error("Error login: {}", ex.getMessage(), ex);
            return buildResponse(messageProperties.messageProperties().getMensajeError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/restaurar")
    public ResponseEntity<ApiResponse<String>>restaurarContrasena(@RequestBody RestaurarContrasenaRequest request) {

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