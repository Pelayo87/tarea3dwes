package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.EjemplarRepository;
import com.pelayora.tarea3dwes.repositorios.PlantaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Service
public class ServicioPlantaImpl implements ServicioPlanta {
	@Autowired
	private PlantaRepository planta_R;
	
	@Autowired
	private EjemplarRepository ejemplar_R;

	Scanner sc = new Scanner(System.in);

	@Override
	public Planta guardarPlanta(Planta planta) {
		return planta_R.saveAndFlush(planta);
	}

	@Override
	public Planta modificarPlanta(Planta planta) {		
	    return planta_R.save(planta);
	}

	@Override
	public Optional<Planta> buscarPlantaPorId(String codigo) {
		return planta_R.findById(codigo);
	}

	@Override
	public List<Planta> listarPlantas() {
		return planta_R.findAll();
	}
	
	public long contadorPlantas() {
        return planta_R.contarPlantas();
    }

	@Override
	public void eliminarPlanta(String codigo) {		
		planta_R.deleteById(codigo);
	}
	
	public boolean tieneEjemplaresAsociados(String codigo) {
	    List<Ejemplar> ejemplares = ejemplar_R.findByPlanta_Codigo(codigo);
	    return !ejemplares.isEmpty();
	}

}