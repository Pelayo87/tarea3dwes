package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Mensaje;

//@Service
public interface ServicioMensaje {

	List<Mensaje> listarMensajes();
	Optional<Mensaje> buscarPorId(long id);
	List<Mensaje> buscarPorPersonaId(long personaId);
	List<Mensaje> buscarPorEjemplarId(long ejemplarId);
	Mensaje guardarMensaje(Mensaje mensaje);
	void eliminarMensaje(long id);
}
