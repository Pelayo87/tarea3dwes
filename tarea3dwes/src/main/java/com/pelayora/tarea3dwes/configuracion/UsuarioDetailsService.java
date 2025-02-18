package com.pelayora.tarea3dwes.configuracion;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;

import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final CredencialesRepository credencialesRepository;

    public UsuarioDetailsService(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credenciales> credenciales = credencialesRepository.findByUsuario(username);

        return credenciales.map((Credenciales credencialesUsuario) -> User.builder()
                .username(credencialesUsuario.getUsuario())
                .password(credencialesUsuario.getPassword())
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    }
}
