package com.pelayora.tarea3dwes.fachada;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioEnfermedad;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioParasitos;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaAdmin {
	@Autowired
	private InvernaderoFachadaPrincipal facade;
	
	@Autowired
	private InvernaderoFachadaPersonal facadePersonal;
	
	Scanner sc = new Scanner(System.in);
	Persona usuarioActual;

    @Autowired
    private ServicioPlanta S_planta;

    @Autowired
    private ServicioCredenciales S_credenciales;

    @Autowired
    private ServicioPersona S_persona;
    
    @Autowired
    private ServicioCliente S_cliente;
    
    @Autowired
    private ServicioFitosanitario S_fitosanitario;
    
    @Autowired
    private ServicioEnfermedad S_enfermedad;
    
    @Autowired
    private ServicioParasitos S_parasitos;

	public void menuadmin() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR INVERNADERO" + " [Usuario actual:" + facade.nombreusuario + "]\n");
		System.out.println("\t\t\t\t1 - GESTIÓN DE PLANTAS");
		System.out.println("\t\t\t\t2 - GESTIÓN DE PERSONAS");
		System.out.println("\t\t\t\t3 - GESTIÓN DE CLIENTES");
		System.out.println("\t\t\t\t4 - GESTIÓN DE EJEMPLARES");
		System.out.println("\t\t\t\t5 - GESTIÓN DE MENSAJES");
		System.out.println("\t\t\t\t6 - GESTIÓN DE FITOSANITARIOS");
		System.out.println("\t\t\t\t7 - GESTIÓN DE ENFERMEDADES");
		System.out.println("\t\t\t\t8 - GESTIÓN DE PARÁSITOS");
		System.out.println("\t\t\t\t9 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t10 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(10);

		switch (opcion) {
		case 1: {
			menuadminplantas();
			break;
		}
		case 2: {
			menuadminpersonas();
			break;
		}
		case 3:{
			menuadminclientes();
		}
		case 4: {
			facadePersonal.gestionEjemplaresmenu();
		}
		case 5: {
			facadePersonal.gestionMensajesmenu();
		}
		case 6: {
			menuadminfitosanitarios();
		}
		case 7: {
			menuadminenfermedades();
		}
		case 8: {
			menuadminparasitos();
		}
		case 9: {
			facade.iniciosesion();
		}			
		case 10: {
			Utilidades.salirdelprograma();
		}
		}
	}

	public void menuadminplantas() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PLANTAS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
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
				menuadminplantas();
			}
			case 2: {
				S_planta.modificarPlanta(null);
				menuadminplantas();
			}
			case 3: {
				S_planta.eliminarPlanta(null);
				menuadminplantas();
			}
			case 4: {
				facade.iniciosesion();
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
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PERSONAS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
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
	
	public void menuadminclientes() {
		int opcion = -1;
		do {
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE CLIENTES" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - VER CLIENTES");
			System.out.println("\t\t\t\t2 - VOLVER ATRÁS");

			opcion = Utilidades.obtenerOpcionUsuario(2);

			switch (opcion) {
			case 1: {
				mostrarDatosClientes();
			}
			}
		} while (opcion != 2);
	}
	
	public void menuadminfitosanitarios() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE FITOSANITARIOS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - AÑADIR FITOSANITARIO");
			System.out.println("\t\t\t\t2 - MODIFICAR FITOSANITARIO");
			System.out.println("\t\t\t\t3 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");
			System.out.println("\t\t\t\t5 - VOLVER ATRÁS");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				S_fitosanitario.guardarFitosanitario(null);
				menuadmin();
			}
			case 2: {
				S_fitosanitario.modificarFitosanitario(null);
				menuadmin();
			}
			case 3: {
				facade.iniciosesion();
				break;
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			case 5: {
				menuadmin();
			}
			}
	}
	
	public void menuadminenfermedades() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE ENFERMEDADES" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - REGISTRAR ENFERMEDAD DE PLANTA");
			System.out.println("\t\t\t\t2 - VOLVER ATRÁS");

			opcion = Utilidades.obtenerOpcionUsuario(2);

			switch (opcion) {
			case 1: {
				registrarEnfermedad();
				menuadmin();
			}
			case 2:{
				menuadmin();
			}
			}
	}
	
	public void menuadminparasitos() {
		int opcion = -1;
		System.out
				.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PARASITOS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
		System.out.println("\t\t\t\t1 - REGISTRAR PARASITOS");
		System.out.println("\t\t\t\t2 - MODIFICAR PARASITOS");
		System.out.println("\t\t\t\t3 - VOLVER ATRÁS");

		opcion = Utilidades.obtenerOpcionUsuario(3);

		switch (opcion) {
		case 1: {
			S_parasitos.guardarParasitos(null);
			menuadmin();
		}
		case 2: {
			S_parasitos.modificarParasitos(null);
		}
		case 3: {
			menuadmin();
		}
		}
	}

	// METODÓS PARA LA GESTIÓN DE PERSONAS Y CLIENTES

	private void registrarPersona() {
	    // Escáner para entrada del usuario
	    Scanner sc = new Scanner(System.in);

	    try {
	        // Guardar Persona y obtener la entidad con el ID generado
	        Persona personaGuardada = S_persona.guardarPersona(null);

	        if (personaGuardada != null && personaGuardada.getId() > 0) {
	            System.out.print("Ingrese el nombre de usuario: ");
	            String usuario = sc.nextLine();

	            System.out.print("Ingrese la contraseña: ");
	            String password = sc.nextLine();

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
	
	private void mostrarDatosClientes() {
		List<Cliente> clientes = S_cliente.listarClientes();

		if (clientes.isEmpty()) {
			System.out.println("No hay datos de ningún cliente disponibles en el sistema.");
			menuadminclientes();
		}

		for (Cliente c: clientes) {
			System.out.println(c.toString());
		}
		
		menuadminclientes();
	}
	
	private void registrarEnfermedad() {
	    Enfermedad enfermedadGuardada = S_enfermedad.guardarEnfermedad(null);

	    System.out.println("¿La enfermedad fue provocada por algún o varios parásitos? (S/N):");
	    String respuesta = sc.nextLine().trim().toUpperCase();
	    
	    if (respuesta.equals("S")) {
	        List<Parasitos> parasitos = S_parasitos.obtenerTodosLosParasitos();
	        
	        if (parasitos.isEmpty()) {
	            System.out.println("No hay parásitos disponibles en el sistema.");
	            menuadminenfermedades();
	            return;
	        }

	        System.out.println("Selecciona el parásito que provoca la enfermedad:");
	        int index = 1;
	        for (Parasitos p : parasitos) {
	            System.out.println(index + " - " + p.getNombre());
	            index++;
	        }

	        boolean seleccionValida = false;
	        while (!seleccionValida) {
	            try {
	                int seleccion = sc.nextInt();
	                sc.nextLine();

	                if (seleccion >= 1 && seleccion <= parasitos.size()) {
	                    Parasitos parasitoSeleccionado = parasitos.get(seleccion - 1);

	                    // Asigno el parásito a la enfermedad
	                    enfermedadGuardada.setParasitos(parasitoSeleccionado);
	                    S_enfermedad.modificarEnfermedad(enfermedadGuardada);

	                    System.out.println("Enfermedad guardada con el parásito asociado: " + parasitoSeleccionado.getNombre());
	                    seleccionValida = true;
	                } else {
	                    System.err.println("Selección no válida. Inténtalo de nuevo.");
	                }
	            } catch (InputMismatchException e) {
	                System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	                sc.nextLine();
	            }
	        }
	    } else if (respuesta.equals("N")) {
	        System.out.println("Enfermedad: " + enfermedadGuardada.getNombre() + " guardada, con síntomas: " + enfermedadGuardada.getSintomas());
	    } else {
	        System.err.println("Respuesta inválida. Debe ser 'S' o 'N'.");
	        registrarEnfermedad();
	    }
	}

}