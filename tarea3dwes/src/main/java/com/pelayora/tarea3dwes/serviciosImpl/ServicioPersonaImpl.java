package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.repositorios.PersonaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;

@Service
public class ServicioPersonaImpl implements ServicioPersona {
	@Autowired
	private PersonaRepository persona_R;

	@Override
	public List<Persona> listarPersonas() {
		return persona_R.findAll();
	}

	@Override
	public Optional<Persona> buscarPorId(Long id) {
		return persona_R.findById(id);
	}

	@Override
	public Persona guardarPersona(Persona persona) {
		return persona_R.save(persona);
	}

	@Override
	public void eliminarPersona(Long id) {
		persona_R.deleteById(id);
	}
}
