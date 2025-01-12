package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Historial;
import com.pelayora.tarea3dwes.repositorios.HistorialRepository;
import com.pelayora.tarea3dwes.servicios.ServicioHistorial;

@Service
public class ServicioHistorialImpl implements ServicioHistorial{
	
	@Autowired
    private HistorialRepository historial_R;
	
	@Override
	public Historial guardarHistorial(Historial historial) {
		return historial_R.saveAndFlush(historial);
	}

	@Override
	public Historial modificarHistorial(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Historial> obtenerHistorialPorId(Long id) {
		return  historial_R.findById(id);
	}

	@Override
	public List<Historial> obtenerHistorialPorNH(String nh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historial> obtenerTodosLosHistoriales() {
		return historial_R.findAll();
	}

	@Override
	public void eliminarHistorial(Long id) {
		historial_R.deleteById(id);
	}

}
