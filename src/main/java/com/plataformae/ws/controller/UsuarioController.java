package com.plataformae.ws.controller;

import com.plataformae.ws.configuration.MessageConfig;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.dto.ApiResponse;
import com.plataformae.ws.service.IUsuarioService;
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
import static com.plataformae.ws.util.Utils.buildResponse;
import static com.plataformae.ws.util.Utils.objectToJsonString;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private MessageConfig messageConfig;


    private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);


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
}