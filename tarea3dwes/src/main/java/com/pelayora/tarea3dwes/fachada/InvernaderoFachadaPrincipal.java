package com.pelayora.tarea3dwes.fachada;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaPrincipal {

    @Autowired
    @Lazy
    private InvernaderoFachadaAdmin facadeAdmin;

    @Autowired
    @Lazy
    private InvernaderoFachadaPersonal facadePersonal;
    @Autowired
    private InvernaderoFachadaInvitado facadeInvitado;

    
    Scanner sc = new Scanner(System.in);
    String nombreusuario;
    public long id_Persona;

    @Autowired
    private ServicioCredenciales S_credenciales;

	public void iniciosesion() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tINICIO DE SESIÓN O REGISTRARSE\n");
		System.out.println("\t\t\t\t1 - LOGIN");
		System.out.println("\t\t\t\t2 - ENTRAR COMO INVITADO");
		System.out.println("\t\t\t\t3 - SALIR");

		opcion = Utilidades.obtenerOpcionUsuario(3);

		switch (opcion) {
		case 1: {
			login();
			break;
		}
		case 2: {
			facadeInvitado.invitado();
			break;
		}
		case 3: {
			Utilidades.salirdelprograma();
		}
		}
	}

	public void login() {
	    System.out.println("Nombre de usuario:");
	    nombreusuario = sc.nextLine().trim();
	    System.out.println("Contraseña (password):");
	    String contrasena = sc.nextLine().trim();

	    Credenciales credencialesIngresadas = new Credenciales(nombreusuario, contrasena);
	    boolean autenticado = S_credenciales.autenticar(credencialesIngresadas);

	    if (autenticado) {
	        Optional<Credenciales> credencialesAutenticadas = S_credenciales.buscarPorUsuario(nombreusuario);
	        if (credencialesAutenticadas.isPresent()) {
	        	id_Persona = credencialesAutenticadas.get().getPersona().getId();
	            if ("admin".equalsIgnoreCase(nombreusuario) && "admin".equals(contrasena)) {
	                System.out.println("Inicio de sesión exitoso como administrador.");
	                facadeAdmin.menuadmin();
	            } else {
	                System.out.println("Inicio de sesión exitoso.");
	                facadePersonal.menu();
	            }
	        }
	    } else {
	        System.out.println("Nombre de usuario o contraseña incorrectos.");
	        iniciosesion();
	    }
	}
	
	public long obtenerPersonaActual() {
        return id_Persona;
    }
}