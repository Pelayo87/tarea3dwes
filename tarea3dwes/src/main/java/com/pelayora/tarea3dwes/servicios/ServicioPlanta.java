package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Planta;

@Service
public interface ServicioPlanta {
	
	Planta guardarPlanta(Planta planta);
	Planta modificarPlanta(String codigo);
    Optional<Planta> buscarPlantaPorId(String codigo);
    List<Planta> listarPlantas();
    void eliminarPlanta(String codigo);
}
