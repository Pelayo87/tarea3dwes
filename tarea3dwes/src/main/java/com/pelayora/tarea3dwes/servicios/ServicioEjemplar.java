package com.pelayora.tarea3dwes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.repositorios.EjemplarRepository;

@Service
public class ServicioEjemplar {

	@Autowired
	EjemplarRepository ejemplar_R;
}
