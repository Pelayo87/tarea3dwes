package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Ejemplar;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-11
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con los ejemplares de plantas. Proporciona métodos 
//para guardar, buscar por ID o planta, listar todos los ejemplares 
//y eliminar ejemplares del sistema.
//--------------------------------------------------------


@Service
public interface ServicioEjemplar {
	
	Ejemplar guardarEjemplar(Ejemplar ejemplar);
    Optional<Ejemplar> obtenerEjemplarPorId(Long id);
    List<Ejemplar> obtenerEjemplarPorPlanta(String codigo);
    List<Ejemplar> obtenerTodosLosEjemplares();
    void eliminarEjemplar(Long id);	
}