package com.pelayora.tarea3dwes.configuracion;

import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class DetallesUsuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	private final Credenciales credenciales;

    public DetallesUsuario(Credenciales credenciales) {
        this.credenciales = credenciales;
    }
    
    public Long getIdPersona() {
        Persona p = credenciales.getPersona();
        return (p != null) ? p.getId() : -1L;
    }
    
    public Long getIdCliente() {
        Cliente c = credenciales.getCliente();
        return (c != null) ? c.getId_cliente() : -1L;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return credenciales.getPassword();
    }

    @Override
    public String getUsername() {
        return credenciales.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}

