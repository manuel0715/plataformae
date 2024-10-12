package com.plataformae.ws.service.impl;

import com.plataformae.ws.configuration.JwtService;
import com.plataformae.ws.db.entity.Rol;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.dto.AuthRequestDTO;
import com.plataformae.ws.dto.AuthResponseDTO;
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
    public void authenticateUser(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );
    }

    @Override
    public AuthResponseDTO createAuthResponse(AuthRequestDTO authRequestDTO) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        if (userDetails instanceof Usuarios usuarios) {
            String token = jwtService.generateToken(usuarios.getUsername(),secret,jwtExpirationInMillis);
            authResponseDTO.setIdentificacion(usuarios.getIdentificacion());
            authResponseDTO.setNombres(usuarios.getNombres());
            authResponseDTO.setApellidos(usuarios.getApellidos());
            authResponseDTO.setUsername(usuarios.getUsername());
            authResponseDTO.setTipoUsuario(usuarios.getTipoUsuario());
            authResponseDTO.setTipoIdentificacion(usuarios.getTipoIdentificacion());
            authResponseDTO.setToken(token);

            List<Integer> roleIds = usuarios.getRoles().stream().map(Rol::getId).toList();
            List<MenuPadreDTO> menuOpciones = roleService.getMenuByRoles(roleIds);
            authResponseDTO.setMenuPadre(menuOpciones);
        }
        return authResponseDTO;
    }

    @Override
    public String generarSesionRegistro(AuthRequestDTO authRequestDTO){
        try {
            authenticateUser(authRequestDTO);
            AuthResponseDTO authResponseDTO = createAuthResponse(authRequestDTO);
            return  authResponseDTO.getToken();
        } catch (Exception ex) {
            LOGGER.error("Error login: {}", ex.getMessage(), ex);
            return "";
        }

    }
}
