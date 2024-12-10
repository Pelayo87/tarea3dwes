package com.pelayora.tarea3dwes.principal;

import java.time.LocalDate;

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

		System.out.println("************************************************");
		System.out.println("* Proyecto: Proyecto en Spring (Tarea 3 DWES)  *");
		System.out.println("* Autor: Pelayo Rodríguez Álvarez              *");
		System.out.printf("* Fecha: %-38s*\n", LocalDate.now().toString());
		System.out.println("************************************************");

		System.out.println("--Bienvenido al sistema del invernadero (DWES)--");

		facade.iniciosesion();
	}
}