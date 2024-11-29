package com.pelayora.tarea3dwes.principal;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
//import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
//import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
//import com.pelayora.tarea3dwes.servicios.ServicioPersona;
//import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

import com.pelayora.tarea3dwes.fachada.InvernaderoFachadaPrincipal;

public class Principal implements CommandLineRunner {
	
//	@Autowired
//	ServicioPlanta S_planta;
//	
//	@Autowired
//	ServicioEjemplar S_ejemplar;
//	
//	@Autowired
//	ServicioPersona S_persona;
//	
//	@Autowired
//	ServicioCredenciales S_credenciales;
//	
//	@Autowired
//	ServicioMensaje S_mensajes;
	
	@Override
	public void run(String... args) throws Exception {
		
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
	}

}
