package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.db.repository.IUsuariosRepository;
import com.plataformae.ws.service.IUsuarioService;
import com.plataformae.ws.util.exeptions.Exceptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private static final Logger LOGGER = LogManager.getLogger(UsuarioServiceImpl.class);

    private final IUsuariosRepository iUsuariosRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(PasswordEncoder passwordEncoder, IUsuariosRepository iUsuariosRepository) {
        this.iUsuariosRepository = iUsuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existeUsuario(String identificacion, String tipoDocumento) {
        return iUsuariosRepository.existsByIdentificacionAndTipoIdentificacion(identificacion, tipoDocumento);
    }

    @Override
    public boolean existeCelular(String identificacion) {
        return iUsuariosRepository.existsByCelular(identificacion);
    }

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getUsername();

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        usuario.setUsername(usuario.getTipoIdentificacion()+usuario.getIdentificacion());
        usuario.setPassword(passwordEncoder.encode(usuario.getIdentificacion()));
        usuario.setUsuarioCreador(userDetails.getUsername());

        try {
            return iUsuariosRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Error: {}",e.getMessage());
            throw new Exceptions("Error al guardar el usuario, por favor comunicate con soporte" );
        }
    }

}