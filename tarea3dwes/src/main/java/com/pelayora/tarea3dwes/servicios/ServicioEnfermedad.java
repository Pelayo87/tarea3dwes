package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Enfermedad;

@Service
public interface ServicioEnfermedad {
	
	Enfermedad guardarEnfermedad(Enfermedad enfermedad);
	Enfermedad modificarEnfermedad(Enfermedad enfermedad);
    Optional<Enfermedad> obtenerEnfermedadesPorId(Long id);
    List<Enfermedad> obtenerEnfermedadesPorNombre(String nombre);
    List<Enfermedad> obtenerEnfermedadesPorSintomas(String sintomas);
    List<Enfermedad> obtenerTodasLasEnfermedades();
    void eliminarEnfermedad(Long id);

}
