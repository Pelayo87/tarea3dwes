package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Persona;

@Service
public interface ServicioPersona {
	
	List<Persona> listarPersonas();
    Optional<Persona> buscarPorId(Long id);
    Persona guardarPersona(Persona persona);
    void eliminarPersona(Long id);
	
}
