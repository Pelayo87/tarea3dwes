package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.PlantaRepository;

@Service
public class ServicioPlantaImpl {
	private final PlantaRepository plantaRepository;

    @Autowired
    public PlantaServiceImpl(PlantaRepository plantaRepository) {
        // Constructor initialization of the final field
        this.plantaRepository = plantaRepository;
    }

    @Override
    public Planta guardarPlanta(Planta planta) {
        return plantaRepository.save(planta);
    }

    @Override
    public Optional<Planta> obtenerPlantaPorId(Long id) {
        return plantaRepository.findById(id);
    }

    @Override
    public List<Planta> obtenerTodasLasPlantas() {
        return plantaRepository.findAll();
    }

    @Override
    public void eliminarPlanta(Long id) {
        plantaRepository.deleteById(id);
    }

}
