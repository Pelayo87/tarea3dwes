package com.pelayora.tarea3dwes.fachada;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioHistorial;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioMensajeImpl;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaPersonal {

	@Autowired
	private InvernaderoFachadaPrincipal facade;
	
	Scanner sc = new Scanner(System.in);
	
	Persona usuarioActual;
	
	private Planta planta;
	private Persona personas;
	private Ejemplar ejemplar;
	private Fitosanitario fitosanitario;

	// FECHA ACTUAL Y FORMATEADA
	Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	String fechaFormateada = formatoFecha.format(fechaActual);

	@Autowired
    private ServicioEjemplar S_ejemplar;
	
	@Autowired
    private ServicioFitosanitario S_fitosanitario;
	
	@Autowired
    private ServicioHistorial S_historial;

    @Autowired
    private ServicioMensaje S_mensaje;
    
    @Autowired
    private ServicioMensajeImpl S_MensajeImpl;

    @Autowired
    private ServicioPersona S_persona;
    
    @Autowired
    private ServicioPlanta S_planta;

	public InvernaderoFachadaPersonal(InvernaderoFachadaPrincipal facade) {
		this.facade = facade;
	}

	public void menu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - GESTIÓN DE EJEMPLARES");
			System.out.println("\t\t\t\t2 - GESTIÓN DE FITOSANITARIOS");
			System.out.println("\t\t\t\t3 - GESTIÓN DE MENSAJES");
			System.out.println("\t\t\t\t4 - Ubicar Ejemplar en localizacion");
			System.out.println("\t\t\t\t5 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t6 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(6);

			switch (opcion) {
			case 1: {
				gestionEjemplaresmenu();
			}
			case 2: {
			   gestionFitosanitariosmenu();
			}
			case 3: {
				gestionMensajesmenu();
			}
			case 4: {
				ubicarEjemplarEnLocalizacion();
			}
			case 5: {
				facade.iniciosesion();
			}
			case 6: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	public void gestionEjemplaresmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE EJEMPLARES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
			System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES");
			System.out.println("\t\t\t\t3 - VER MENSAJES DE UN EJEMPLAR");
			System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				registrarEjemplar();
				gestionEjemplaresmenu();
			}
			case 2: {
				filtrarEjemplaresmenu();
			}
			case 3: {
				vermensajesEjemplar();
				gestionEjemplaresmenu();
			}
			case 4: {
				facade.iniciosesion();
			}
			case 5: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	public void gestionFitosanitariosmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE FITOSANITARIOS" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - APLICAR FITOSANITARIO A UN EJEMPLAR");
			System.out.println("\t\t\t\t2 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t3 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(3);

			switch (opcion) {
			case 1: {
				aplicarFitosaniarioAEjemplar();
				gestionFitosanitariosmenu();
			}
			case 4: {
				facade.iniciosesion();
			}
			case 5: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void filtrarEjemplaresmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR EJEMPLARES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - MOSTRAR TODOS LOS EJEMPLARES");
			System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES POR TIPO/S DE PLANTA/S");
			System.out.println("\t\t\t\t3 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");			

			opcion = Utilidades.obtenerOpcionUsuario(4);

			switch (opcion) {
			case 1: {
				filtrarEjemplarestodos();
				filtrarEjemplaresmenu();
			}
			case 2: {
				filtrarEjemplarestipoplanta();
				filtrarEjemplaresmenu();
			}
			case 3: {
				gestionEjemplaresmenu();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void gestionMensajesmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTION DE MENSAJES/ANOTACIONES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - REALIZAR ANOTACIONES EN FORMA DE MENSAJES");
			System.out.println("\t\t\t\t2 - MOSTRAR TODOS LOS MENSAJES/ANOTACIONES");
			System.out.println("\t\t\t\t3 - FILTRAR ANOTACIONES/MENSAJES");
			System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				realizarAnotaciones();
				gestionMensajesmenu();
			}
			case 2: {
				mostrarAnotaciones();
				gestionMensajesmenu();
			}
			case 3: {
				filtrarAnotacionesmenu();
				gestionMensajesmenu();
			}
			case 4: {
				menu();
			}
			case 5: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void filtrarAnotacionesmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR MENSAJES/ANOTACIONES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - FILTRAR POR PERSONA QUE LO ESCRIBIO");
			System.out.println("\t\t\t\t2 - FILTRAR POR RANGO DE FECHA");
			System.out.println("\t\t\t\t3 - FILTRAR POR TIPO DE PLANTA");
			System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");
			

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				filtrarAnotacionesporPersona();
				break;
			}
			case 2: {
				S_MensajeImpl.filtrarAnotacionesporRangoFecha();
				break;
			}
			case 3: {
				filtrarAnotacionesporTipoPlanta();
			}
			case 4: {
				gestionMensajesmenu();
			}
			case 5: {
				Utilidades.salirdelprograma();
			}			
			}
	}

	// MÉTODOS PARA LA GESTIÓN DE EJEMPLARES

	private void registrarEjemplar() {
	    System.out.println("Selecciona el tipo de planta:");
	    eligePlanta();

	    try {
	    	sc.nextLine();
	        System.out.println("Planta seleccionada: " + planta.getNombreComun());

	        Ejemplar nuevoEjemplar = new Ejemplar();
	        nuevoEjemplar.setPlanta(planta);
	        nuevoEjemplar = S_ejemplar.guardarEjemplar(nuevoEjemplar);

	        String nombreEjemplar = planta.getCodigo() + "_" + nuevoEjemplar.getId();
	        nuevoEjemplar.setNombre(nombreEjemplar);
	        S_ejemplar.guardarEjemplar(nuevoEjemplar);

	        Mensaje mensajeInicial = new Mensaje();
	        mensajeInicial.setFechahora(new Date()); // Fecha actual
	        mensajeInicial.setMensaje("Registro realizado por " + facade.nombreusuario + " el " + new Date());

	        // Asocio el ejemplar y la persona
	        mensajeInicial.setEjemplar(nuevoEjemplar);
	        Persona personaActual = new Persona();
	        personaActual.setId(facade.obtenerPersonaActual());
	        mensajeInicial.setPersona(personaActual);


	        // Guardo el mensaje
	        S_mensaje.guardarMensaje(mensajeInicial);

	        System.out.println("Ejemplar registrado exitosamente con nombre: " + nombreEjemplar);
	        gestionEjemplaresmenu();
	    } catch (Exception e) {
	        System.err.println("Ocurrió un error al registrar el ejemplar: " + e.getMessage());
	    }
	}

	private void vermensajesEjemplar() {
		System.out.println("Selecciona el ejemplar del que deseas ver los mensajes:");
		eligeEjemplar();
		try {
			sc.nextLine();
			long idEjemplar = ejemplar.getId();

			// Obtengo los mensajes relacionados con el ejemplar seleccionado
			List<Mensaje> mensajes = S_mensaje.buscarPorEjemplarId(idEjemplar);

			if (mensajes.isEmpty()) {
				System.out.println("No hay mensajes para el ejemplar seleccionado.");
				gestionEjemplaresmenu();
			} else {
				System.out.println("Mensajes para el ejemplar " + ejemplar.getNombre() + ":");
				for (Mensaje mensaje : mensajes) {
					System.out.println("Fecha y hora: " + mensaje.getFechahora());
					System.out.println("Mensaje: " + mensaje.getMensaje());
					System.out.println("-------------------------");
				}
				gestionEjemplaresmenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void filtrarEjemplarestodos() {
		List<Ejemplar> ejemplares = S_ejemplar.obtenerTodosLosEjemplares();

		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			filtrarEjemplaresmenu();
		}

		for (Ejemplar e : ejemplares) {
			System.out.println(e.toString());
		}
		
		filtrarEjemplaresmenu();

	}

	private void filtrarEjemplarestipoplanta() {
		System.out.println("Selecciona el tipo de planta que deseas ver sus ejemplares:");
		eligePlanta();

		try {
			sc.nextLine();
			String tipo_Planta = planta.getCodigo();

			// Obtengo todos los ejemplares del tipo de planta seleccionado
			List<Ejemplar> ejemplaresTipoPlanta = S_ejemplar.obtenerEjemplarPorPlanta(tipo_Planta);
			if (ejemplaresTipoPlanta.isEmpty()) {
				System.out.println("No hay ejemplares para el tipo de planta seleccionado.");
				filtrarEjemplaresmenu();
			} else {
				for (Ejemplar ejemplar : ejemplaresTipoPlanta) {
					System.out.println(ejemplar.toString());
				}
				filtrarEjemplaresmenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}
	
	// MÉTODOS PARA LA GESTIÓN DE FITOSANITARIOS
	
	private void aplicarFitosaniarioAEjemplar() {

	    System.out.println("Selecciona el ejemplar al que deseas aplicar un fitosanitario:");
	    eligeEjemplar();
	    
	    sc.nextLine();
	    
	    System.out.println("Selecciona el fitosanitario que deseas aplicar al ejemplar " + ejemplar.getNombre() + ":");
	    eligeFitosanitario();
	    
	    sc.nextLine();

	    fitosanitario.getEjemplares().add(ejemplar);

	    S_fitosanitario.aplicarFitosanitarioAejemplar(fitosanitario);
	    
	    String nombreEjemplar = ejemplar.getNombre();

	    System.out.println("El fitosanitario " + fitosanitario.getNombre() +
	                       " ha sido aplicado al ejemplar " + nombreEjemplar + ".");
	    
	    String nh = "NH_" + nombreEjemplar;
	    LocalDate actualizado = LocalDate.now();
	    
	    Historial historialEjemplar = new Historial();
	    historialEjemplar.setNh(nh);
	    historialEjemplar.setActualizado(actualizado);
	    historialEjemplar.setEjemplar(ejemplar);
	    
	    S_historial.guardarHistorial(historialEjemplar);
	    
	    Mensaje mensajeHistorial = new Mensaje();
        mensajeHistorial.setFechahora(new Date()); // Fecha actual
        mensajeHistorial.setMensaje("Se aplica " +  fitosanitario.getNombre() + " por " + facade.nombreusuario 
                                     + " a fecha " + new Date());
        
        mensajeHistorial.setEjemplar(ejemplar);
        mensajeHistorial.setHistorial(historialEjemplar);
        Persona personaActual = new Persona();
        personaActual.setId(facade.obtenerPersonaActual());
        mensajeHistorial.setPersona(personaActual);
        
        // Guardo el mensaje
        S_mensaje.guardarMensaje(mensajeHistorial);

	    gestionFitosanitariosmenu();
	}



	// MÉTODOS PARA LA GESTIÓN DE MENSAJES

	private void realizarAnotaciones() {
		
		System.out.println("Selecciona el ejemplar del que deseas realizar una anotación:");
		eligeEjemplar();

	    try {
	    	 sc.nextLine(); 

	         System.out.println("Escribe la anotación sobre el ejemplar:");
	         String anotacionUsuario = sc.nextLine().trim();

	         if (anotacionUsuario.isEmpty()) {
	             System.out.println("La anotación no puede estar vacía. Inténtalo de nuevo.");
	             realizarAnotaciones();
	         }
	        
	        Mensaje anotacion = new Mensaje();
	        anotacion.setFechahora(new Date());
	        anotacion.setMensaje(anotacionUsuario);
	        anotacion.setEjemplar(ejemplar);

	        // Obtengo la persona actual desde la fachada
	        Persona personaActual = new Persona();
	        personaActual.setId(facade.obtenerPersonaActual());
	        anotacion.setPersona(personaActual);

	        // Guardo el mensaje en la base de datos
	        S_mensaje.guardarMensaje(anotacion);

	        System.out.println("Anotación registrada con éxito para el ejemplar: " + ejemplar.getNombre());
	    } catch (Exception e) {
	        System.out.println("Ocurrió un error al registrar la anotación: " + e.getMessage());
	    }
	}


	private void mostrarAnotaciones() {
		List<Mensaje> mensajes = S_mensaje.listarMensajes();

		if (mensajes.isEmpty()) {
			System.out.println("No hay anotaciones de ejemplares disponibles en el sistema.");
			gestionMensajesmenu();
		}

		for (Mensaje m : mensajes) {
			System.out.println(m.toString());
		}
		
		gestionMensajesmenu();
	}
	
	//FILTROS PARA MOSTRAR ANOTACIONES EN ESPECÍFICO

	private void filtrarAnotacionesporPersona() {

		System.out.println("Selecciona la persona de la que quieres ver las anotaciones:");
		
		eligePersona();

		long idPersona = personas.getId();

		List<Mensaje> anotacionesporPersona = S_mensaje.buscarPorPersonaId(idPersona);
		for (Mensaje m : anotacionesporPersona) {
			System.out.println(m.toString());
		}
		filtrarAnotacionesmenu();
	}

	protected void filtrarAnotacionesporTipoPlanta() {

		    System.out.println("Selecciona el tipo de planta de la que quieres ver anotaciones:");
		    eligePlanta();
			String codigo_planta = planta.getCodigo();

			// Obtener todos los ejemplares que tengan el código de la planta seleccionada
			List<Ejemplar> codigo_plantaEjemplar = S_ejemplar.obtenerEjemplarPorPlanta(codigo_planta);

			// Extraer los IDs de los ejemplares
			List<Mensaje> anotacionesPorTipoPlanta = new ArrayList<>(new HashSet<Mensaje>());
			for (Ejemplar ejemplar : codigo_plantaEjemplar) {
				anotacionesPorTipoPlanta = S_mensaje.buscarPorEjemplarId(ejemplar.getId());
			}

			// Mostrar las anotaciones obtenidas
			if (anotacionesPorTipoPlanta.isEmpty()) {
				System.out.println("No hay anotaciones para el tipo de planta seleccionado.");
				filtrarAnotacionesmenu();
			} else {
				for (Mensaje m : anotacionesPorTipoPlanta) {
					System.out.println(m.toString());
				}
				filtrarAnotacionesmenu();
			}
	}

	//UBICAR EJEMPLARES EN LOCALIZACION
	
	private void ubicarEjemplarEnLocalizacion() {

		System.out.println("Selecciona el ejemplar que deseas ubicar en una localización:");
		eligeEjemplar();
	}
	
	
	//METODOS PARA ELEGIR UNO/VARIOS DE LA LISTA
	
	protected void eligePersona() {
		List<Persona> listaPersonas = S_persona.listarPersonas();

		int index = 1;
		for (Persona p : listaPersonas) {
			System.out.println(index + " - " + p.getNombre());
			index++;
		}

		// Obtener la selección del usuario
		try {
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > listaPersonas.size()) {
				System.err.println("Selección no válida. Por favor, elige un número entre 1 y " + listaPersonas.size() + ".");
				filtrarAnotacionesmenu();
			}

			// Obtener el ejemplar seleccionado
			personas = (Persona) listaPersonas.toArray()[seleccion - 1];
		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void eligeEjemplar() {
		List<Ejemplar> listaEjemplares = S_ejemplar.obtenerTodosLosEjemplares();

		int index = 1;
		for (Ejemplar e : listaEjemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}

		try {
			// Obtengo la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > listaEjemplares.size()) {
				System.out.println("Selección no válida.");
				menu();
			}

			// Obtengo la planta seleccionada
			ejemplar = (Ejemplar) listaEjemplares.toArray()[seleccion - 1];
		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	protected void eligePlanta() {
		List<Planta> listaPlantas = S_planta.listarPlantas();

		int index = 1;
		for (Planta p : listaPlantas) {
			System.out.println(index + " - " + p.getNombreComun());
			index++;
		}

		try {
			// Obtengo la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > listaPlantas.size()) {
				System.out.println("Selección no válida.");
				filtrarAnotacionesmenu();
			}

			// Actualizo la planta seleccionada en el atributo de la clase
			planta = listaPlantas.get(seleccion - 1);
		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}
	
	private void eligeFitosanitario() {
		List<Fitosanitario> listaFitosanitarios = S_fitosanitario.obtenerTodosLosFitosanitarios();

		int index = 1;
		for (Fitosanitario f : listaFitosanitarios) {
			System.out.println(index + " - " + f.getNombre());
			index++;
		}

		try {
			// Obtengo la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > listaFitosanitarios.size()) {
				System.out.println("Selección no válida.");
				menu();
			}

			// Obtengo el fitosanitario seleccionado
			fitosanitario = (Fitosanitario) listaFitosanitarios.toArray()[seleccion - 1];
		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}
}