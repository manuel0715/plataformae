package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.AuthResponse;
import com.plataformae.ws.dto.RestaurarContrasenaRequest;
import com.plataformae.ws.service.IAuthService;
import com.plataformae.ws.service.IUsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import static com.plataformae.ws.util.Utils.buildResponse;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Value("${jwt.secret}")
    private  String secret;

    private final MessageConfig messageProperties;
    private final IAuthService iAuthService;
    private final IUsuarioService iUsuarioService;

    @Autowired
    public AuthController(MessageConfig messageProperties, IAuthService iAuthService, IUsuarioService iUsuarioService) {
        this.messageProperties = messageProperties;
        this.iAuthService = iAuthService;
        this.iUsuarioService = iUsuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        LOGGER.info("REQUEST /api/auth/login: {}", authRequest.getUsername());

        try {
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

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!request.getEmail().matches(regex)){
            return buildResponse(
                    "Email Invalido",
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        if(!iUsuarioService.existeEmail(request.getEmail(),request.getIdentificacion(),request.getTipoIdentificacion())){
            return buildResponse(
                    "El correo ingresado no corresponde al numero de documento",
                    null,
                    HttpStatus.BAD_REQUEST
            );

        }
        return null;
    }

}