package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Ejemplar;

@Service
public interface ServicioEjemplar {
	
	Ejemplar guardarEjemplar(Ejemplar ejemplar);
    Optional<Ejemplar> obtenerEjemplarPorId(Long id);
    List<Ejemplar> obtenerEjemplarPorPlanta(String codigo);
    List<Ejemplar> obtenerTodosLosEjemplares();
    void eliminarEjemplar(Long id);	
}