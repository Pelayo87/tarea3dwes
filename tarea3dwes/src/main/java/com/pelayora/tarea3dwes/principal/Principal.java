package com.pelayora.tarea3dwes.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.fachada.InvernaderoFachadaPrincipal;

@Component
public class Principal implements CommandLineRunner {
	
	
	@Autowired
	InvernaderoFachadaPrincipal facade;
	
	@Override
	public void run(String... args) throws Exception {
		
//        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
//        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
	}
}
