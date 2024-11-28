package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.PlantaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Service
public class ServicioPlantaImpl implements ServicioPlanta{
	@Autowired
	private PlantaRepository planta_R;

    @Override
    public Planta guardarPlanta(Planta planta) {
        return planta_R.save(planta);
    }

    @Override
    public Optional<Planta> obtenerPlantaPorId(Long id) {
        return planta_R.findById(id);
    }

    @Override
    public List<Planta> obtenerTodasLasPlantas() {
        return planta_R.findAll();
    }

    @Override
    public void eliminarPlanta(Long id) {
        planta_R.deleteById(id);
    }

}
