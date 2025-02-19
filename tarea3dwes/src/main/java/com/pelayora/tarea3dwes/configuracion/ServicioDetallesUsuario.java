package com.pelayora.tarea3dwes.configuracion;

import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    @Autowired
    private ServicioCredenciales S_credenciales;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {       
        Optional<Credenciales> credenciales = S_credenciales.buscarPorUsuario(username);
        if (!credenciales.isPresent()) {
            System.err.println("Usuario no encontrado");
        }
        return new DetallesUsuario(credenciales.get());
    }
}
