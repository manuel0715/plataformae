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
import org.springframework.transaction.annotation.Transactional;

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

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        String userName = usuario.getTipoIdentificacion()+usuario.getIdentificacion();
        usuario.setUsername(userName);
        usuario.setPassword(passwordEncoder.encode(usuario.getIdentificacion()));
        usuario.setUsuarioCreador(userName);

        try {
            return iUsuariosRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Error: {}",e.getMessage());
            throw new Exceptions("Error al guardar el usuario, por favor comunicate con soporte" );
        }
    }

    @Override
    public boolean existeEmail(String email, String identificacion,String tipoIdentificacion) {
        return iUsuariosRepository.existsByEmailAndIdentificacionAndTipoIdentificacion(email,identificacion,tipoIdentificacion);
    }

    @Override
    public Usuarios buscarUsuario(Usuarios usuarios) {
        return iUsuariosRepository.findUsuariosByIdentificacionAndTipoIdentificacion(usuarios.getIdentificacion(),usuarios.getTipoIdentificacion());
    }

}