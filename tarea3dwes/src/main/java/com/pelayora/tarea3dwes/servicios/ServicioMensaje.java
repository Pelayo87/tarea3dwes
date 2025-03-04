package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Mensaje;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con los mensajes. Proporciona métodos para listar, 
//buscar por ID, filtrar por persona o ejemplar, guardar nuevos 
//mensajes y eliminar mensajes existentes.
//--------------------------------------------------------


@Service
public interface ServicioMensaje {

	List<Mensaje> listarMensajes();
	Optional<Mensaje> buscarPorId(long id);
	List<Mensaje> buscarPorPersonaId(long personaId);
	List<Mensaje> buscarPorEjemplarId(long ejemplarId);
	Mensaje guardarMensaje(Mensaje mensaje);
	void eliminarMensaje(long id);
	long contadorMensajes();
	List<Mensaje> obtenerMensajePorNombreEjemplar(String nombre);
	List<Mensaje> obtenerMensajePorNombrePersona(String nombre);
	List<Mensaje> obtenerMensajePorTipoPlanta(String nombreComun);
	int contarMensajes(Long idEjemplar);
	Optional<Mensaje> obtenerUltimoMensaje(Long idEjemplar);
}
