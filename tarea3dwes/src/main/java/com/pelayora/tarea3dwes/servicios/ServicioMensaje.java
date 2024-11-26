package com.pelayora.tarea3dwes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.repositorios.MensajeRepository;

@Service
public class ServicioMensaje {

	@Autowired
	MensajeRepository mensaje_R;
}
