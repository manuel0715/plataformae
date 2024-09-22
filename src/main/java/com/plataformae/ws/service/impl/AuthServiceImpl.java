package com.plataformae.ws.service.impl;

import com.plataformae.ws.configuration.JwtService;
import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Rol;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.AuthResponse;
import com.plataformae.ws.dto.MenuPadreDTO;
import com.plataformae.ws.service.IAuthService;
import com.plataformae.ws.service.IRolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthServiceImpl.class);

    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.expiration}")
    private  long jwtExpirationInMillis;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final IRolService roleService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtService jwtService, IRolService roleService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.roleService = roleService;
    }

    @Override
    public void authenticateUser(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
    }

    @Override
    public AuthResponse createAuthResponse(AuthRequest authRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        AuthResponse authResponse = new AuthResponse();

        if (userDetails instanceof Usuarios usuarios) {
            String token = jwtService.generateToken(usuarios.getUsername(),secret,jwtExpirationInMillis);
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

    @Override
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
