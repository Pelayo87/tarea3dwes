package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Fitosanitario;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con los fitosanitarios de los ejemplares. Proporciona métodos 
//para guardar, buscar por ID o nombre, listar todos los fitosanitarios 
//y eliminar fitosanitarios del sistema.
//--------------------------------------------------------

@Service
public interface ServicioFitosanitario {

	Fitosanitario guardarFitosanitario(Fitosanitario fitosanitario);
	Fitosanitario modificarFitosanitario(Long id);
	Fitosanitario aplicarFitosanitarioAejemplar(Fitosanitario fitosanitario);
    Optional<Fitosanitario> obtenerFitosanitariosPorId(Long id);
    List<Fitosanitario> obtenerFitosanitariosPorNombre(String nombre);
    List<Fitosanitario> obtenerTodosLosFitosanitarios();
    void eliminarFitosanitario(Long id);	
    long contadorFitosanitarios();
}

