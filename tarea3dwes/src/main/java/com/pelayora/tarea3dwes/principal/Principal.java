package com.pelayora.tarea3dwes.principal;

import org.springframework.boot.CommandLineRunner;

import com.pelayora.tarea3dwes.fachada.InvernaderoFachadaPrincipal;

public class Principal implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
	}
}
