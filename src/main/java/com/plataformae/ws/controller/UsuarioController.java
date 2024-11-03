package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.domain.EmailService;
import com.plataformae.ws.dto.ApiResponseDTO;
import com.plataformae.ws.dto.AuthRequestDTO;
import com.plataformae.ws.dto.RestaurarContrasenaRequestDTO;
import com.plataformae.ws.dto.UsuarioResponseDTO;
import com.plataformae.ws.service.IAuthService;
import com.plataformae.ws.service.IUsuarioService;
import com.plataformae.ws.util.AuthUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.plataformae.ws.util.Utils.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final MessageConfig messageConfig;
    private final IAuthService iAuthService;
    private final EmailService emailService;

    private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, MessageConfig messageConfig, IAuthService iAuthService, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.messageConfig = messageConfig;
        this.iAuthService = iAuthService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/crear" ,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDTO<Usuarios>> crearUsuario(@RequestBody Usuarios request) {

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

            AuthRequestDTO authRequestDTO = new AuthRequestDTO();
            authRequestDTO.setUsername(nuevoUsuario.getUsername());
            authRequestDTO.setPassword(nuevoUsuario.getIdentificacion());
            nuevoUsuario.setToken(iAuthService.generarSesionRegistro(authRequestDTO));

            String body="¡Hola " + nuevoUsuario.getPrimerNombre() + "!\n\n" +
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
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> buscarUsuario(@RequestBody Usuarios request) {

        if (!usuarioService.existeUsuario(request.getIdentificacion(), request.getTipoIdentificacion())) {
            return buildResponse(
                    "No se encontro Usuario con la información diligenciada.",
                    null,
                    HttpStatus.CONFLICT
            );
        }

        Usuarios usuario = usuarioService.buscarUsuario(request);
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                usuario.getIdentificacion(),
                usuario.getEstado(),
                usuario.getTipoIdentificacion(),
                usuario.getPrimerNombre(),
                usuario.getPrimerApellido(),
                usuario.getEmail(),
                maskEmail(usuario.getEmail()),
                maskPhoneNumber(usuario.getCelular()),
                usuario.getTipoUsuario(),
                usuario.getFechaCreacion(),
                usuario.getUsername()
        );

                return buildResponse(
                        "Consulta éxitosa",
                        usuarioResponseDTO,
                        HttpStatus.OK
                );


    }

}