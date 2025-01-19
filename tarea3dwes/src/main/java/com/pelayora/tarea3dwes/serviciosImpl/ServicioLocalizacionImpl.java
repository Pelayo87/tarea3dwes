package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Localizacion;
import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;
import com.pelayora.tarea3dwes.repositorios.LocalizacionRepository;
import com.pelayora.tarea3dwes.servicios.ServicioLocalizacion;

import jakarta.transaction.Transactional;

@Service
public class ServicioLocalizacionImpl implements ServicioLocalizacion{
	
	@Autowired
    private LocalizacionRepository localizacion_R;

	@Override
	public Localizacion guardarLocalizacion(Localizacion localizacion) {
		return localizacion_R.save(localizacion);
	}

	@Override
	public Optional<Localizacion> obtenerLocalizacionPorId(Long id) {
		return localizacion_R.findById(id);
	}

	@Override
	public List<Localizacion> obtenerLocalizacionPorNumSeccion(int numSeccion) {
		return null;
	}
	

	@Override
	public List<Localizacion> obtenerTodasLasLocalizacion() {
		return localizacion_R.findAll();
	}

	@Override
	public void eliminarLocalizacion(Long id) {
		localizacion_R.deleteById(id);
		
	}

	@Override
	public List<Localizacion> obtenerLocalizacionesSinEjemplar() {
		return localizacion_R.findLocalizacionesSinEjemplar();
	}

}
