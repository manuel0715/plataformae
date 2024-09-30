package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.JwtService;
import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.domain.EmailService;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.dto.AuthRequest;
import com.plataformae.ws.dto.UsuarioResponse;
import com.plataformae.ws.service.IAuthService;
import com.plataformae.ws.service.IUsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.List;

import static com.plataformae.ws.util.Utils.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final MessageConfig messageConfig;
    private final IAuthService iAuthService;
    private final JwtService jwtService;
    private final EmailService emailService;

    private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, MessageConfig messageConfig, IAuthService iAuthService, JwtService jwtService, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.messageConfig = messageConfig;
        this.iAuthService = iAuthService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/crear" ,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Usuarios>> crearUsuario(@RequestBody Usuarios request) {

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("REQUEST /crear: {}", objectToJsonString(request));
            }

            if (usuarioService.existeUsuario(request.getIdentificacion(), request.getTipoIdentificacion())) {
                return buildResponse(
                        messageConfig.messageProperties().getUsuarioYaExiste(),
                        null,
                        HttpStatus.CONFLICT
                );
            }

           Usuarios nuevoUsuario = usuarioService.crearUsuario(request);

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(nuevoUsuario.getUsername());
            authRequest.setPassword(nuevoUsuario.getIdentificacion());
            nuevoUsuario.setToken(iAuthService.generarSesionRegistro(authRequest));

            String body="¡Hola " + nuevoUsuario.getNombres() + "!\n\n" +
                    "Tu registro ha sido exitoso. A continuación, tus credenciales:\n" +
                    "Usuario: " + nuevoUsuario.getUsername() + "\n" +
                    "Contraseña: " + nuevoUsuario.getIdentificacion() + "\n\n" +
                    "Recuerda que puedes cambiar tu contraseña al iniciar sesión por primera vez o desde la opción 'Olvidé mi contraseña'.\n\n" +
                    "Por favor, mantén esta información segura.\n\n" +
                    "Saludos,\n" +
                    "Equipo de Soporte.";
            emailService.sendEmail(nuevoUsuario.getEmail(),"Registro Exitoso",body);

            return buildResponse(
                    messageConfig.messageProperties().getUsuarioCreado(),
                    nuevoUsuario,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            LOGGER.error("Error al crear usuario: {}",e.getMessage());
            return buildResponse(
                    messageConfig.messageProperties().getMensajeError() +": "+ e.getMessage(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @PostMapping(value = "/buscar" ,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UsuarioResponse>> buscarUsuario(@RequestBody Usuarios request) {

        if (!usuarioService.existeUsuario(request.getIdentificacion(), request.getTipoIdentificacion())) {
            return buildResponse(
                    "No se encontro Usuario con la información diligenciada.",
                    null,
                    HttpStatus.CONFLICT
            );
        }

        Usuarios usuario = usuarioService.buscarUsuario(request);
        UsuarioResponse usuarioResponse = new UsuarioResponse(
                usuario.getIdentificacion(),
                usuario.getEstado(),
                usuario.getTipoIdentificacion(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                maskEmail(usuario.getEmail()),
                maskPhoneNumber(usuario.getCelular()),
                usuario.getTipoUsuario(),
                usuario.getFechaCreacion(),
                usuario.getUsername()
        );

                return buildResponse(
                        "Consulta éxitosa",
                        usuarioResponse,
                        HttpStatus.OK
                );


    }
}