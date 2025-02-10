package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Historial;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con el historial de los ejemplares. Proporciona métodos 
//para guardar, buscar por ID o NH, obtener todos los historiales
//y eliminar historial de un ejemplar por ID.
//--------------------------------------------------------

@Service
public interface ServicioHistorial {

	Historial guardarHistorial(Historial historial);
	Historial modificarHistorial(Long id);
	Optional<Historial> obtenerHistorialPorId(Long id);
	List<Historial> obtenerHistorialPorNH(String nh);
	List<Historial> obtenerTodosLosHistoriales();
	void eliminarHistorial(Long id);
}

