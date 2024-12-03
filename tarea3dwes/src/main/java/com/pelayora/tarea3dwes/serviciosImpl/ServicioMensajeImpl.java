package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.repositorios.MensajeRepository;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;

@Service
public class ServicioMensajeImpl implements ServicioMensaje {

	@Autowired
    private MensajeRepository mensajeRepository;
	
    @Override
    public List<Mensaje> listarMensajes() {
        return mensajeRepository.findAll();
    }

    @Override
    public Optional<Mensaje> buscarPorId(long id) {
        return mensajeRepository.findById(id);
    }

    @Override
    public List<Mensaje> buscarPorPersonaId(long personaId) {
        return mensajeRepository.findByPersonaId(personaId);
    }

    @Override
    public List<Mensaje> buscarPorEjemplarId(long ejemplarId) {
        return mensajeRepository.findByEjemplarId(ejemplarId);
    }

    @Override
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public void eliminarMensaje(long id) {
        mensajeRepository.deleteById(id);
    }

}
