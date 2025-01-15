package com.pelayora.tarea3dwes.fachada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.util.Utilidades;

@Component
public class InvernaderoFachadaCliente {
	
	@Autowired
	private InvernaderoFachadaPrincipal facade;
	
	@Autowired
    private ServicioPlanta S_planta;
	
	@Autowired
    private ServicioCliente S_cliente;
	
	Scanner sc = new Scanner(System.in);
	Persona usuarioActual;
	
	public void menuCliente() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - GESTIÓN DE MIS PLANTAS FAVORITAS");
			System.out.println("\t\t\t\t2 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t3 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				gestionPlantasFavoritas();
				menuCliente();
			}
			case 2: {
				facade.iniciosesion();
			}
			case 3: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	public void gestionPlantasFavoritas() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - AÑADIR PLANTAS FAVORITAS");
			System.out.println("\t\t\t\t1 - VER MIS PLANTAS FAVORITAS");
			System.out.println("\t\t\t\t3 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				anadirPlantasFavoritas();
				menuCliente();
			}
			case 2: {
				verPlantasFavoritas();
				menuCliente();
			}
			case 3: {
				facade.iniciosesion();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	private void verPlantasFavoritas() {
	    System.out.println("--TUS PLANTAS FAVORITAS (LISTADO)--");
	    int index = 1;

	    try {
	        Optional<Cliente> clienteActual = S_cliente.buscarPorId(facade.id_Cliente);
	        
	        if (clienteActual.isPresent()) {
	            Cliente cliente = clienteActual.get();
	            List<Planta> plantasFavoritas = cliente.getPlantas();

	            // Verifico si el cliente tiene plantas favoritas
	            if (plantasFavoritas.isEmpty()) {
	                System.out.println("No tienes plantas favoritas añadidas.");
	            } else {
	                for (Planta planta : plantasFavoritas) {
	                    System.out.println(index + " - " + planta.getNombreComun() + " (" + planta.getNombreCientifico() + ")");
	                    index++;
	                }
	            }
	        } else {
	            System.err.println("Cliente no encontrado.");
	        }
	    } catch (RuntimeException e) {
	        System.err.println("Error al obtener las plantas favoritas: " + e.getMessage());
	    }
	}

	
	private void anadirPlantasFavoritas() {
		System.out.println("--AÑADIR PLANTAS FAVORITAS--");

		List<Planta> plantas = S_planta.listarPlantas();
		if (plantas.isEmpty()) {
			System.out.println("No hay plantas disponibles en el sistema.");
			gestionPlantasFavoritas();
		}

		System.out.println("Selecciona la planta que quieres añadir a tus favoritas:");
		int index = 1;
		for (Planta p : plantas) {
			System.out.println(index + " - " + p.getNombreComun());
			index++;
		}
		try {
			// Obtener la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > plantas.size()) {
				System.out.println("Selección no válida.");
				gestionPlantasFavoritas();
			}

			// Obtengo la planta seleccionada
			Planta plantaSeleccionada = (Planta) plantas.toArray()[seleccion - 1];

			Optional<Cliente> clienteActual = S_cliente.buscarPorId(facade.id_Cliente);

			if (clienteActual.isPresent()) {
				Cliente cliente = clienteActual.get();

				/*
				 * Añado la planta seleccionada a la lista existente y guardo el cliente con la
				 * planta favorita que ha selsccionado
				 */

				cliente.getPlantas().add(plantaSeleccionada);
				cliente.setId_cliente(facade.id_Cliente);
				S_cliente.guardarPlantasFavoritasCliente(cliente);

			} else {
				System.err.println("Cliente no encontrado.");
			}
			long id_Cliente = facade.id_Cliente;
			System.out.println("( " + id_Cliente + " ) " + facade.nombreusuario + " has añadido la planta: "
					+ plantaSeleccionada.getNombreComun() + " a tus favoritas.");

		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

}
