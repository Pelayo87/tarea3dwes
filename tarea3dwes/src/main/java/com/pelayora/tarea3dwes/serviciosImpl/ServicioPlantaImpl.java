package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.PlantaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Service
public class ServicioPlantaImpl implements ServicioPlanta {
	@Autowired
	private PlantaRepository planta_R;

	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
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
}