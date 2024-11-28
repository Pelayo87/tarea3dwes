package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.repositorios.EjemplarRepository;

@Service
public class ServicioEjemplarImpl {
	private final EjemplarRepository ejemplarRepository;

    @Autowired
    public EjemplarServiceImpl(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public Ejemplar guardarEjemplar(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    @Override
    public Optional<Ejemplar> obtenerEjemplarPorId(Long id) {
        return ejemplarRepository.findById(id);
    }

    @Override
    public List<Ejemplar> obtenerTodosLosEjemplares() {
        return ejemplarRepository.findAll();
    }

    @Override
    public void eliminarEjemplar(Long id) {
        ejemplarRepository.deleteById(id);
    }

}
