package com.plataformae.ws.service.impl;


import com.plataformae.ws.db.entity.Usuarios;
import com.plataformae.ws.db.repository.IUsuariosRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUsuariosRepository iUsuariosRepository;

    public UserDetailsServiceImpl(IUsuariosRepository iUsuariosRepository) {
        this.iUsuariosRepository = iUsuariosRepository;
    }

    @Override
    public Usuarios loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUsuariosRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
