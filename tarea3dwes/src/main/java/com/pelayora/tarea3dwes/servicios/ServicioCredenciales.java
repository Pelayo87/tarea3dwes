package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Credenciales;

@Service
public interface ServicioCredenciales {
	
	List<Credenciales> listarCredenciales();
    Optional<Credenciales> buscarPorId(long id);
    Optional<Credenciales> buscarPorUsuario(String usuario);
    Credenciales guardarCredenciales(Credenciales credenciales);
    void eliminarCredenciales(long id);
    boolean autenticar(Credenciales credenciales);
}
