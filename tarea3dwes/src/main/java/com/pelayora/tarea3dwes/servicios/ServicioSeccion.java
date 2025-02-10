package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Seccion;

@Service
public interface ServicioSeccion {
	
	Seccion guardarSeccion(Seccion seccion);
	Optional<Seccion> obtenerSeccionPorId(Long id);
	List<Seccion> obtenerSeccionPorNombre(String nombre);
	List<Seccion> obtenerTodasLasSecciones();
	void eliminarSeccion(Long id);

}
