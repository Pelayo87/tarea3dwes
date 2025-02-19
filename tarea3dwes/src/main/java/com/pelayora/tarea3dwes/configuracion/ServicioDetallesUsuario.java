package com.pelayora.tarea3dwes.configuracion;

import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    @Autowired
    private ServicioCredenciales S_credenciales;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Caso especial para el usuario "admin"
        if ("admin".equalsIgnoreCase(username)) {
            Credenciales CredencialesAdmin = new Credenciales();
            CredencialesAdmin.setUsuario("admin");
            CredencialesAdmin.setPassword(passwordEncoder.encode("admin"));
            return new DetallesUsuario(CredencialesAdmin);
        }
        
        Optional<Credenciales> credenciales = S_credenciales.buscarPorUsuario(username);
        if (!credenciales.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new DetallesUsuario(credenciales.get());
    }
}
