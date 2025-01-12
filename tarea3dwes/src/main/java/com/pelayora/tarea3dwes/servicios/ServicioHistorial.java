package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Historial;

@Service
public interface ServicioHistorial {

	Historial guardarHistorial(Historial historial);
	Historial modificarHistorial(Long id);
	Optional<Historial> obtenerHistorialPorId(Long id);
	List<Historial> obtenerHistorialPorNH(String nh);
	List<Historial> obtenerTodosLosHistoriales();
	void eliminarHistorial(Long id);
}

