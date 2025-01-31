package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.RelRolesUsuarios;
import com.plataformae.ws.db.entity.Rol;
import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.db.repository.IRelRolesUsuariosRepository;
import com.plataformae.ws.db.repository.IUsuariosRepository;
import com.plataformae.ws.dto.ApiResponsePageDTO;
import com.plataformae.ws.service.IUsuarioService;
import com.plataformae.ws.util.exeptions.Exceptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.plataformae.ws.util.Utils.buildResponsePage;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private static final Logger LOGGER = LogManager.getLogger(UsuarioServiceImpl.class);

    private final IUsuariosRepository iUsuariosRepository;
    private final IRelRolesUsuariosRepository rolesUsuariosRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(PasswordEncoder passwordEncoder, IUsuariosRepository iUsuariosRepository, IRelRolesUsuariosRepository rolesUsuariosRepository) {
        this.iUsuariosRepository = iUsuariosRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesUsuariosRepository = rolesUsuariosRepository;
    }

    @Override
    public boolean existeUsuario(String identificacion, String tipoDocumento) {
        return iUsuariosRepository.existsByIdentificacionAndTipoIdentificacion(identificacion, tipoDocumento);
    }

    @Override
    public boolean existeUsuario(String usuario) {
        return iUsuariosRepository.existsByUsername(usuario);
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

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }

        if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El email debe ser válido.");
        }

        if (usuario.getCelular() == null || usuario.getCelular().trim().isEmpty()) {
            throw new IllegalArgumentException("El número celular debe ser válido (ejemplo: 3001234567).");
        }
        String regex ="^3\\d{9}$";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(usuario.getCelular()).matches()) {
            throw new IllegalArgumentException("El número celular debe ser válido (ejemplo: 3001234567).");
        }

        String userName = usuario.getIdentificacion();
        usuario.setUsername(userName);
        usuario.setPassword(passwordEncoder.encode(usuario.getIdentificacion()));
        usuario.setUsuarioCreador(userName);

        Rol rol = new Rol();
        if (usuario.getTipoUsuario().equals("E")){
            rol.setId(2);
            Set<Rol> roles = new HashSet<>();
            roles.add(rol);
            usuario.setRoles(roles);
        }else{
            rol.setId(1);
            Set<Rol> roles = new HashSet<>();
            roles.add(rol);
            usuario.setRoles(roles);
        }

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

    @Override
    public int updatePasword(String username, String password) {
        return iUsuariosRepository.updatePasword(username,passwordEncoder.encode(password));
    }


    @Transactional
    @Override
    public Usuarios actualizarPerfil(String authenticatedUser, Usuarios request) {
         iUsuariosRepository.updatePerfilUsuario(request.getPrimerNombre(), request.getSegundoNombre(),request.getPrimerApellido(),request.getSegundoApellido(),
                request.getNombreCompleto(),request.getDepartamentoExpedicion().getId().intValue(),request.getMunicipioExpedicion().getId().intValue(),request.getFechaNacimiento(),
                request.getDepartamentoNacimiento().getId().intValue(),request.getMunicipioNacimiento().getId().intValue(),request.getEmail(),request.getCelular()
                ,request.getIdentificacion());

         return iUsuariosRepository.findByIdentificacion(request.getIdentificacion());

    }

    @Override
    public Usuarios cargarInformacionPerfil(String authenticatedUser) {
        return iUsuariosRepository.findByUsername(authenticatedUser)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public ResponseEntity<ApiResponsePageDTO<List<Usuarios>>> cargarUsuarios(int page, int size,String tipoUsuario) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nombreCompleto"));

        Page<Usuarios> usuariosPage;
        if (tipoUsuario != null && !tipoUsuario.isEmpty()) {
            // Filtrar por tipo de usuario si está presente
            usuariosPage = iUsuariosRepository.findByTipoUsuario(tipoUsuario, pageRequest);
        } else {
            // Obtener todos los usuarios si no hay filtro
            usuariosPage = iUsuariosRepository.findAll(pageRequest);
        }

        List<Usuarios> list = usuariosPage.getContent();

        return buildResponsePage("Ok",list, HttpStatus.OK,usuariosPage.getTotalElements(),usuariosPage.getTotalPages());
    }

}