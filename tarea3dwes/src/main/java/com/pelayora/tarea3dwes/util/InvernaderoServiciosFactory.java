package com.pelayora.tarea3dwes.util;

import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioCredencialesImpl;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioEjemplarImpl;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioMensajeImpl;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioPersonaImpl;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioPlantaImpl;

public class InvernaderoServiciosFactory {
	public static InvernaderoServiciosFactory servicios;
	
	public static InvernaderoServiciosFactory getServicios() {
		if (servicios ==null)
			servicios=new InvernaderoServiciosFactory();
		return servicios;
	}
	
	public ServicioEjemplar getServiciosEjemplar() {
		return new ServicioEjemplarImpl();
	}
	
	public ServicioPlanta getServiciosPlanta() {
		return new ServicioPlantaImpl();
	}
	
	public ServicioMensaje getServiciosMensaje() {
		return new ServicioMensajeImpl();
	}
	
	public ServicioCredenciales getServiciosCredenciales() {
		return new ServicioCredencialesImpl();
	}
	
	public ServicioPersona getServiciosPersona() {
		return new ServicioPersonaImpl();
	}

}
