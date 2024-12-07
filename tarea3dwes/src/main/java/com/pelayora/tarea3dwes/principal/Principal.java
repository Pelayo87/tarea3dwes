package com.pelayora.tarea3dwes.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.fachada.InvernaderoFachadaPrincipal;

@Component
public class Principal implements CommandLineRunner {	
	
	@Autowired
	public InvernaderoFachadaPrincipal facade;
	
	@Override
	public void run(String... args) throws Exception {
     
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
	}
}
