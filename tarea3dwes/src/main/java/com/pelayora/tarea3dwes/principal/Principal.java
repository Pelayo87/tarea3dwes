package com.pelayora.tarea3dwes.principal;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Principal implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		System.out.println("************************************************");
		System.out.println("* Proyecto: Proyecto en Spring (Tarea 3 DWES)  *");
		System.out.println("* Autor: Pelayo Rodríguez Álvarez              *");
		System.out.printf("* Fecha: %-38s*\n", LocalDate.now().toString());
		System.out.println("************************************************");

		System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
		
		 try {
	            String url = "http://localhost:8080/inicio";
	            System.out.println("\n\t\t\t\033[1;36mLink de Entrada --> " + url + "\033[0m");
	            
	            ProcessBuilder pb = new ProcessBuilder();
	            String os = System.getProperty("os.name").toLowerCase();
	            if (os.contains("win")) {
	                pb.command("cmd", "/c", "start", url);
	            } else if (os.contains("mac")) {
	                pb.command("open", url);
	            } else if (os.contains("nix") || os.contains("nux")) {
	                pb.command("xdg-open", url);
	            } else {
	                throw new UnsupportedOperationException("Sistema operativo no soportado para abrir navegadores.");
	            }
	            pb.start();
	            System.out.println("\n\t\t\t\033[1;32mAbriendo el navegador con el enlace...\033[0m");
	        } catch (Exception e) {
	            System.out.println("\n\t\t\t\033[1;31mError al intentar abrir el navegador:\033[0m");
	            e.printStackTrace();
	        }
	}
}