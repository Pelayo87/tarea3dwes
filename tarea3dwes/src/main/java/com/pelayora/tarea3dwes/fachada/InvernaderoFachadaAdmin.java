package com.pelayora.tarea3dwes.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaAdmin {
	private InvernaderoFachadaPrincipal facade;
	private InvernaderoFachadaPersonal facadePersonal;
	
	Scanner sc = new Scanner(System.in);
	String nombreusuario;
	Persona usuarioActual;

    @Autowired
    private ServicioPlanta S_planta;

    @Autowired
    private ServicioCredenciales S_credenciales;

    @Autowired
    private ServicioPersona S_persona;

	public void menuadmin() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR INVERNADERO\n");
		System.out.println("\t\t\t\t1 - GESTIÓN DE PLANTAS");
		System.out.println("\t\t\t\t2 - GESTIÓN DE PERSONAS");
		System.out.println("\t\t\t\t3 - GESTIÓN DE EJEMPLARES");
		System.out.println("\t\t\t\t4 - GESTIÓN DE MENSAJES");
		System.out.println("\t\t\t\t5 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t6 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(6);

		switch (opcion) {
		case 1: {
			menuadminplantas();
			break;
		}
		case 2: {
			menuadminpersonas();
			break;
		}
		case 3: {
			facadePersonal.gestionEjemplaresmenu();
		}
		case 4: {
			facadePersonal.gestionMensajesmenu();
		}
		case 5: {
			facade.iniciosesion();
		}			
		case 6: {
			Utilidades.salirdelprograma();
		}
		}
	}

	public void menuadminplantas() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PLANTAS\n");
			System.out.println("\t\t\t\t1 - AÑADIR PLANTA");
			System.out.println("\t\t\t\t2 - MODIFICAR PLANTA");
			System.out.println("\t\t\t\t3 - BORRAR PLANTA");
			System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");
			System.out.println("\t\t\t\t6 - VOLVER ATRÁS");

			opcion = Utilidades.obtenerOpcionUsuario(6);

			switch (opcion) {
			case 1: {
				S_planta.guardarPlanta(null);
				break;
			}
			case 2: {
				S_planta.modificarPlanta(null);
				break;
			}
			case 3: {
				S_planta.eliminarPlanta(null);
				break;
			}
			case 4: {
				facade.iniciosesion();
				;
				break;
			}
			case 5: {
				Utilidades.salirdelprograma();
			}
			case 6: {
				menuadmin();
			}
			}
	}

	public void menuadminpersonas() {
		int opcion = -1;
		do {
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PERSONAS\n");
			System.out.println("\t\t\t\t1 - REGISTRAR PERSONA");
			System.out.println("\t\t\t\t2 - VOLVER ATRÁS");

			opcion = Utilidades.obtenerOpcionUsuario(2);

			switch (opcion) {
			case 1: {
				registrarPersona();
				break;
			}
			}
		} while (opcion != 2);
	}

	// METODÓS PARA LA GESTIÓN DE PERSONAS

	private void registrarPersona() {
	    // Escáner para entrada del usuario
	    Scanner scanner = new Scanner(System.in);

	    try {
	        // Recoger datos de la persona
	        System.out.print("Ingrese el nombre de la persona: ");
	        String nombre = scanner.nextLine();

	        System.out.print("Ingrese el email de la persona: ");
	        String email = scanner.nextLine();

	        // Creación de la entidad Persona
	        Persona persona = new Persona();
	        persona.setNombre(nombre);
	        persona.setEmail(email);

	        // Guardar Persona y obtener la entidad con el ID generado
	        Persona personaGuardada = S_persona.guardarPersona(persona);

	        if (personaGuardada != null && personaGuardada.getId() > 0) {
	            System.out.print("Ingrese el nombre de usuario: ");
	            String usuario = scanner.nextLine();

	            System.out.print("Ingrese la contraseña: ");
	            String password = scanner.nextLine();

	            // Creación de la entidad Credenciales vinculada a la Persona
	            Credenciales credenciales = new Credenciales();
	            credenciales.setUsuario(usuario);
	            credenciales.setPassword(password);
	            credenciales.setPersona(personaGuardada);

	            // Guardar Credenciales
	            Credenciales credencialesGuardadas = S_credenciales.guardarCredenciales(credenciales);

	            if (credencialesGuardadas != null && credencialesGuardadas.getId() > 0) {
	                System.out.println("Persona y credenciales registradas correctamente.");
	            } else {
	                System.err.println("Error al registrar las credenciales.");
	            }
	        } else {
	            System.err.println("Error al registrar la persona.");
	        }
	    } catch (Exception e) {
	        System.err.println("Ocurrió un error: " + e.getMessage());
	    }
	}

}
