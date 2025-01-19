package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Localizacion;
import com.pelayora.tarea3dwes.modelo.Seccion;

@Service
public interface ServicioLocalizacion {
	
	Localizacion guardarLocalizacion(Localizacion localizacion);
    Optional<Localizacion> obtenerLocalizacionPorId(Long id);
    List<Localizacion> obtenerLocalizacionPorNumSeccion(int numSeccion);
    List<Localizacion> obtenerLocalizacionesPorSeccion(Seccion seccion);
    List<Localizacion> obtenerLocalizacionesSinEjemplar();
    List<Localizacion> obtenerTodasLasLocalizacion();
    void eliminarLocalizacion(Long id);	

}
