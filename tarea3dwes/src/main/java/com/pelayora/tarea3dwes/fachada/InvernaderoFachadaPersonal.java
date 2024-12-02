package com.pelayora.tarea3dwes.fachada;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.serviciosImpl.ServicioMensajeImpl;
import com.pelayora.tarea3dwes.util.InvernaderoServiciosFactory;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaPersonal {
	private InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);
	String nombreusuario;
	Persona usuarioActual;

	// FECHA ACTUAL Y FORMATEADA
	Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	String fechaFormateada = formatoFecha.format(fechaActual);

	InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

	@Autowired
    private ServicioEjemplar S_ejemplar;

    @Autowired
    private ServicioPlanta S_planta;

    @Autowired
    private ServicioMensaje S_mensaje;

    @Autowired
    private ServicioCredenciales S_credenciales;

    @Autowired
    private ServicioPersona S_persona;

	public InvernaderoFachadaPersonal(InvernaderoFachadaPrincipal facade) {
		this.facade = facade;
	}

	public void menu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO\n");
			System.out.println("\t\t\t\t1 - GESTIÓN DE EJEMPLARES");
			System.out.println("\t\t\t\t2 - GESTIÓN DE MENSAJES");
			System.out.println("\t\t\t\t3 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(4);

			switch (opcion) {
			case 1: {
				gestionEjemplaresmenu();
				break;
			}
			case 2: {
				gestionMensajesmenu();
				break;
			}
			case 3: {
				facade.iniciosesion();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	public void gestionEjemplaresmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE EJEMPLARES\n");
			System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
			System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES");
			System.out.println("\t\t\t\t3 - VER MENSAJES DE UN EJEMPLAR");
			System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				registrarEjemplar();
				break;
			}
			case 2: {
				filtrarEjemplaresmenu();
				break;
			}
			case 3: {
				vermensajesEjemplar();
				break;
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
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR EJEMPLARES\n");
			System.out.println("\t\t\t\t1 - MOSTRAR TODOS LOS EJEMPLARES");
			System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES POR TIPO/S DE PLANTA/S");
			System.out.println("\t\t\t\t3 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");			

			opcion = Utilidades.obtenerOpcionUsuario(4);

			switch (opcion) {
			case 1: {
				filtrarEjemplarestodos();
				break;
			}
			case 2: {
				filtrarEjemplarestipoplanta();
				break;
			}
			case 3: {
				menu();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void gestionMensajesmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTION DE MENSAJES/ANOTACIONES\n");
			System.out.println("\t\t\t\t1 - REALIZAR ANOTACIONES EN FORMA DE MENSAJES");
			System.out.println("\t\t\t\t2 - MOSTRAR TODOS LOS MENSAJES/ANOTACIONES");
			System.out.println("\t\t\t\t3 - FILTRAR ANOTACIONES/MENSAJES");
			System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				realizarAnotaciones();
				break;
			}
			case 2: {
				mostrarAnotaciones();
				break;
			}
			case 3: {
				filtrarAnotacionesmenu();
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
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR MENSAJES/ANOTACIONES\n");
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
//				ServicioMensajeImpl.filtrarAnotacionesporRangoFecha();
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
//		List<Planta> tiposPlantas = S_planta.obtenerTodasLasPlantas();
//		if (tiposPlantas.isEmpty()) {
//			System.out.println("No hay tipos de plantas disponibles en el sistema.");
//			gestionEjemplaresmenu();
//		}
//		System.out.println("Selecciona el tipo de planta:");
//		int index = 1;
//		for (Planta planta : tiposPlantas) {
//			System.out.println(index + " - " + planta.getNombrecomun());
//			index++;
//		}
//		try {
//			// Obtener la selección del usuario
//			int seleccion = sc.nextInt();
//			if (seleccion < 1 || seleccion > tiposPlantas.size()) {
//				System.out.println("Selección no válida.");
//				return;
//			}
//			// Obtener la planta seleccionada
//			Planta plantaElegida = (Planta) tiposPlantas.toArray()[seleccion - 1];
//			System.out.println("Planta seleccionada: " + plantaElegida.getNombrecomun());
//			String codigoPlanta = plantaElegida.getCodigo();
//			// Crear instancia de Ejemplar
//			Ejemplar nuevoEjemplar = new Ejemplar();
//			nuevoEjemplar.setNombre("");
//			nuevoEjemplar.setCodigo(codigoPlanta);
//			// Guardo el ejemplar en la BD
//			S_ejemplar.insertar(nuevoEjemplar);
//
//			// Recuperar todos los ejemplares para encontrar el último ID
//			Set<Ejemplar> mostrarEjemplares = S_ejemplar.findAll();
//			long idEjemplar = -1;
//			for (Ejemplar ejemplar : mostrarEjemplares) {
//				if (ejemplar.getId() > idEjemplar) {
//					idEjemplar = ejemplar.getId();
//				}
//			}
//			if (idEjemplar <= 0) {
//				System.err.println("Error: No se pudo obtener el ID del ejemplar registrado.");
//				gestionEjemplaresmenu();
//			}
//
//			String nombreEjemplar = codigoPlanta + "_" + idEjemplar;
//			//System.out.println(idEjemplar);
//
//			Ejemplar cambioejemplar = new Ejemplar(idEjemplar, nombreEjemplar, codigoPlanta);
//			// Modifico el ejemplar en la BD
//			S_ejemplar.modificar(cambioejemplar);
//
//			// Creo el mensaje inicial para el ejemplar registrado
//			Mensaje mensajeInicial = new Mensaje();
//			mensajeInicial.setFechahora(fechaActual);
//			mensajeInicial.setMensaje("Registro realizado por " + nombreusuario + " el " + fechaFormateada);
//			mensajeInicial.setId_ejemplar(idEjemplar);
//			mensajeInicial.setId_persona(facade.id_Persona);
//			// Guardo el mensaje en la BD
//			int resultadoMensaje = S_mensaje.insertar(mensajeInicial);
//			if (resultadoMensaje > 0) {
//				System.out.println("Ejemplar y mensaje inicial registrados exitosamente.");
//				gestionEjemplaresmenu();
//			} else {
//				System.err.println("Ocurrió un error al registrar el mensaje.");
//			}
//		} catch (InputMismatchException e) {
//			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
//			sc.nextLine();
//		}
	}

	private void vermensajesEjemplar() {
		List<Ejemplar> ejemplares = S_ejemplar.obtenerTodosLosEjemplares();
		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			gestionEjemplaresmenu();
		}

		System.out.println("Selecciona el ejemplar del que deseas ver los mensajes:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}
		try {
			// Obtener la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.err.println("Selección no válida.");
				return;
			}

			// Obtener el ejemplar seleccionado
			Ejemplar ejemplarElegido = (Ejemplar) ejemplares.toArray()[seleccion - 1];
			long idEjemplar = ejemplarElegido.getId();

			// Obtener los mensajes relacionados con el ejemplar seleccionado
			List<Mensaje> mensajes = S_mensaje.buscarPorEjemplarId(idEjemplar);

			if (mensajes.isEmpty()) {
				System.out.println("No hay mensajes para el ejemplar seleccionado.");
				gestionEjemplaresmenu();
			} else {
				System.out.println("Mensajes para el ejemplar " + ejemplarElegido.getNombre() + ":");
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
//		List<Planta> plantas = S_planta.obtenerTodasLasPlantas();
//		if (plantas.isEmpty()) {
//			System.out.println("No hay plantas disponibles en el sistema.");
//			return;
//		}
//
//		System.out.println("Selecciona el tipo de planta que deseas ver sus ejemplares:");
//		int index = 1;
//		for (Planta p : plantas) {
//			System.out.println(index + " - " + p.getNombrecomun());
//			index++;
//		}
//		try {
//			// Obtener la selección del usuario
//			int seleccion = sc.nextInt();
//			if (seleccion < 1 || seleccion > plantas.size()) {
//				System.out.println("Selección no válida.");
//				return;
//			}
//
//			// Obtener el tipo de planta seleccionado
//			Planta tipoplanta = (Planta) plantas.toArray()[seleccion - 1];
//			String tipo_Planta = tipoplanta.getCodigo();
//
//			// Obtener todos los ejemplares del tipo de planta seleccionado
//			List<Ejemplar> ejemplaresTipoPlanta = S_ejemplar.findByPlanta(tipo_Planta);
//			if (ejemplaresTipoPlanta.isEmpty()) {
//				System.out.println("No hay ejemplares para el tipo de planta seleccionado.");
//				filtrarEjemplaresmenu();
//			} else {
//				for (Ejemplar ejemplar : ejemplaresTipoPlanta) {
//					System.out.println(ejemplar.toString());
//				}
//				filtrarEjemplaresmenu();
//			}
//		} catch (InputMismatchException e) {
//			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
//			sc.nextLine();
//		}
	}

	// MÉTODOS PARA LA GESTIÓN DE MENSAJES

	private void realizarAnotaciones() {
//		List<Ejemplar> ejemplares = S_ejemplar.obtenerTodosLosEjemplares();
//
//		if (ejemplares.isEmpty()) {
//			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
//			gestionMensajesmenu();
//		}
//
//		System.out.println("Selecciona el ejemplar del que deseas realizar una anotación:");
//		int index = 1;
//		for (Ejemplar e : ejemplares) {
//			System.out.println(index + " - " + e.getNombre());
//			index++;
//		}
//
//		try {
//			// Obtener la selección del usuario
//			int seleccion = sc.nextInt();
//			sc.nextLine(); // Consumir el salto de línea restante
//
//			if (seleccion < 1 || seleccion > ejemplares.size()) {
//				System.out.println("Selección no válida.");
//				return;
//			}
//
//			// Obtener el ejemplar seleccionado
//			Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
//			long idEjemplar = ejemplar.getId();
//
//			System.out.println("Escribe la anotación sobre el ejemplar:");
//			String anotacionUsuario = sc.nextLine().trim();
//
//			// Crear el mensaje (anotación) sobre ese ejemplar
//			Mensaje anotacion = new Mensaje();
//			anotacion.setFechahora(fechaActual);
//			anotacion.setMensaje(anotacionUsuario);
//			anotacion.setId_ejemplar(idEjemplar);
//			anotacion.setId_persona(facade.id_Persona);
//
//			// Guardo el mensaje en la BD
//			S_mensaje.guardarMensaje(anotacion);
//			gestionMensajesmenu();
//		} catch (InputMismatchException e) {
//			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
//			sc.nextLine();
//		}
		
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
		List<Persona> listaPersonas = S_persona.listarPersonas();

		System.out.println("Selecciona la persona de la que quieres ver las anotaciones:");
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
			Persona personas = (Persona) listaPersonas.toArray()[seleccion - 1];
			long idPersona = personas.getId();

			List<Mensaje> anotacionesporPersona = S_mensaje.buscarPorPersonaId(idPersona);
			for (Mensaje m : anotacionesporPersona) {
				System.out.println(m.toString());
			}
			filtrarAnotacionesmenu();

		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void filtrarAnotacionesporTipoPlanta() {
//		List<Planta> listaPlantas = S_planta.obtenerTodasLasPlantas();
//
//		System.out.println("Selecciona el tipo de planta de la que quieres ver anotaciones:");
//		int index = 1;
//		for (Planta p : listaPlantas) {
//			System.out.println(index + " - " + p.getNombrecomun());
//			index++;
//		}
//
//		try {
//			// Obtener la selección del usuario
//			int seleccion = sc.nextInt();
//			if (seleccion < 1 || seleccion > listaPlantas.size()) {
//				System.out.println("Selección no válida.");
//				filtrarAnotacionesmenu();
//			}
//
//			// Obtener la planta seleccionada
//			Planta planta = (Planta) listaPlantas.toArray()[seleccion - 1];
//			String codigo_planta = planta.getCodigo();
//
//			// Obtener todos los ejemplares que tengan el código de la planta seleccionada
//			List<Ejemplar> codigo_plantaEjemplar = S_ejemplar(codigo_planta);
//
//			// Extraer los IDs de los ejemplares
//			List<Mensaje> anotacionesPorTipoPlanta = (List<Mensaje>) new HashSet<Mensaje>();
//			for (Ejemplar ejemplar : codigo_plantaEjemplar) {
//				anotacionesPorTipoPlanta = S_mensaje.buscarPorEjemplarId(ejemplar.getId());
//			}
//
//			// Mostrar las anotaciones obtenidas
//			if (anotacionesPorTipoPlanta.isEmpty()) {
//				System.out.println("No hay anotaciones para el tipo de planta seleccionado.");
//				filtrarAnotacionesmenu();
//			} else {
//				for (Mensaje m : anotacionesPorTipoPlanta) {
//					System.out.println(m.toString());
//				}
//				filtrarAnotacionesmenu();
//			}
//		} catch (InputMismatchException e) {
//			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
//			sc.nextLine();
//		}
	}
}
