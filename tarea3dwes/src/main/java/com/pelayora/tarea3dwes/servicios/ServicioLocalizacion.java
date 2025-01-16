package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Localizacion;

@Service
public interface ServicioLocalizacion {
	
	Localizacion guardarLocalizacion(Localizacion localizacion);
    Optional<Localizacion> obtenerLocalizacionPorId(Long id);
    List<Localizacion> obtenerLocalizacionPorNumSeccion(int numSeccion);
    List<Localizacion> obtenerTodasLasLocalizacion();
    void eliminarLocalizacion(Long id);	

}
