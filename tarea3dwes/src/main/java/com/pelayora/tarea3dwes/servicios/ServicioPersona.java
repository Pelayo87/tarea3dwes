package com.pelayora.tarea3dwes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.repositorios.PersonaRepository;

@Service
public class ServicioPersona {
	
	@Autowired
	PersonaRepository persona_R;
}
