package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Parasitos;

@Service
public interface ServicioParasitos {
	
	Parasitos guardarParasitos(Parasitos parasitos);
	Parasitos modificarParasitos(Long id);
    Optional<Parasitos> obtenerParasitosPorId(Long id);
    List<Parasitos> obtenerParasitosPorNombre(String nombre);
    List<Parasitos> obtenerParasitosPorColor(String color);
    List<Parasitos> obtenerTodosLosParasitos();
    void eliminarEnfermedad(Long id);

}
