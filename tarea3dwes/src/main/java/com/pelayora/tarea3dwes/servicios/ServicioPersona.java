package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Persona;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-11
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con las personas. Proporciona métodos para listar 
//todas las personas, buscar por ID, guardar nuevas personas y 
//eliminar personas existentes.
//--------------------------------------------------------


@Service
public interface ServicioPersona {
	
	List<Persona> listarPersonas();
    Optional<Persona> buscarPorId(Long id);
    Persona guardarPersona(Persona persona);
    void eliminarPersona(Long id);
    boolean existPersonaPorEmail(String email);
    Optional<Persona> buscarPorNombre(String nombre);
	
}