package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Fitosanitario;

@Service
public interface ServicioFitosanitario {

	Fitosanitario guardarFitosanitario(Fitosanitario fitosanitario);
	Fitosanitario modificarFitosanitario(Long id);
    Optional<Fitosanitario> obtenerFitosanitariosPorId(Long id);
    List<Fitosanitario> obtenerFitosanitariosPorNombre(String nombre);
    List<Fitosanitario> obtenerTodosLosFitosanitarios();
    void eliminarFitosanitario(Long id);	
}

