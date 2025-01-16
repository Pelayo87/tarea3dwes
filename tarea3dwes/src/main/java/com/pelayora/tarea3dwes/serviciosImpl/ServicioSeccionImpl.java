package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Seccion;
import com.pelayora.tarea3dwes.repositorios.SeccionRepository;
import com.pelayora.tarea3dwes.servicios.ServicioSeccion;

@Service
public class ServicioSeccionImpl implements ServicioSeccion{
	
	@Autowired
    private SeccionRepository seccion_R;

	@Override
	public Seccion guardarSeccion(Seccion seccion) {
		return seccion_R.save(seccion);
	}

	@Override
	public Optional<Seccion> obtenerSeccionPorId(Long id) {
		return seccion_R.findById(id);
	}

	@Override
	public List<Seccion> obtenerSeccionPorNombre(String nombre) {
		return seccion_R.findSeccionByNombre(nombre);
	}

	@Override
	public List<Seccion> obtenerTodasLasSecciones() {
		return seccion_R.findAll();
	}

	@Override
	public void eliminarSeccion(Long id) {
		seccion_R.deleteById(id);
	}

}
