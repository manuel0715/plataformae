package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.JwtService;
import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.db.entity.Rol;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.AuthResponse;
import com.plataformae.ws.dto.MenuPadreDTO;
import com.plataformae.ws.service.IRolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final MessageConfig messageProperties;
    private final IRolService roleService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtService jwtService,
                          MessageConfig messageProperties, IRolService roleService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.messageProperties = messageProperties;
        this.roleService = roleService;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        LOGGER.info("REQUEST /login: {}", authRequest.getUsername());

        try {
            authenticateUser(authRequest);
            AuthResponse authResponse = createAuthResponse(authRequest);
            return buildResponse(messageProperties.messageProperties().getLoginExitoso(), authResponse, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return buildResponse(messageProperties.messageProperties().getCredencialesInvalidas(), null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            LOGGER.error("Error login: {}", ex.getMessage(), ex);
            return buildResponse(messageProperties.messageProperties().getMensajeError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void authenticateUser(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
    }

    private AuthResponse createAuthResponse(AuthRequest authRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        AuthResponse authResponse = new AuthResponse();

        if (userDetails instanceof Usuarios usuarios) {
            String token = jwtService.generateToken(userDetails);
            authResponse.setIdentificacion(usuarios.getIdentificacion());
            authResponse.setNombres(usuarios.getNombres());
            authResponse.setApellidos(usuarios.getApellidos());
            authResponse.setUsername(usuarios.getUsername());
            authResponse.setTipoUsuario(usuarios.getTipoUsuario());
            authResponse.setTipoIdentificacion(usuarios.getTipoIdentificacion());
            authResponse.setToken(token);

            List<Integer> roleIds = usuarios.getRoles().stream().map(Rol::getId).collect(Collectors.toList());
            List<MenuPadreDTO> menuOpciones = roleService.getMenuByRoles(roleIds);
            authResponse.setMenuPadre(menuOpciones);
        }
        return authResponse;
    }

    private ResponseEntity<ApiResponse<AuthResponse>> buildResponse(String message, AuthResponse authResponse, HttpStatus status) {
        ApiResponse<AuthResponse> response = new ApiResponse<>(message, authResponse, status.value());
        return new ResponseEntity<>(response, status);
    }

    public String generarSesionRegistro(AuthRequest authRequest){

        try {
            authenticateUser(authRequest);
            AuthResponse authResponse = createAuthResponse(authRequest);
            return  authResponse.getToken();
        } catch (Exception ex) {
            LOGGER.error("Error login: {}", ex.getMessage(), ex);
            return "";
        }

    }
}