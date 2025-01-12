package com.pelayora.tarea3dwes.fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.util.*;

@Component
public class InvernaderoFachadaInvitado {
	@Autowired
	@Lazy
	private InvernaderoFachadaPrincipal invernaderoFachadaPrincipal;
	
	Scanner sc = new Scanner(System.in);
    String nombreusuario;
    Persona usuarioActual;    

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioCredenciales S_credenciales;
    
    @Autowired
    private ServicioCliente S_cliente;
       
    public void invitado() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - VER PLANTAS");
            System.out.println("\t\t\t\t2 - LOGIN");
            System.out.println("\t\t\t\t3 - REGISTRARSE (NUEVO CLIENTE)");
            System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");
            System.out.println("\t\t\t\t5 - VOLVER ATRAS");

            opcion = Utilidades.obtenerOpcionUsuario(5);

            switch (opcion) {
                case 1: {
                    mostrarPlantas();
                    break;
                }
                case 2: {
					invernaderoFachadaPrincipal.login();
                    break;
                }
                case 3:{
                	registrarCliente();
                }
                case 4: {
                	Utilidades.salirdelprograma();
                }
            }
        } while (opcion != 5);
    }
    
    
    
    //MÉTODOS PARA LA GESTIÓN DE PLANTAS

    private void mostrarPlantas() {        
        List<Planta> plantasSet = S_planta.listarPlantas();
        List<Planta> Listaplantas = new ArrayList<>(plantasSet);

        Collections.sort(Listaplantas, new Comparator<Planta>() {
            @Override
            public int compare(Planta p1, Planta p2) {
                return p1.getNombreComun().compareToIgnoreCase(p2.getNombreComun());
            }
        });

        // Mostrar las plantas en orden alfabético
        for (Planta planta : Listaplantas) {
            System.out.println(planta);
        }
    } 
    
    private void registrarCliente() {
	    // Escáner para entrada del usuario
	    Scanner sc = new Scanner(System.in);

	    try {
	        // Guardar Cliente y obtener la entidad con el ID generado
	        Cliente clienteGuardado = S_cliente.guardarCliente(null);

	        if (clienteGuardado != null && clienteGuardado.getId_cliente() > 0) {
	            System.out.print("Ingrese el nombre de usuario: ");
	            String usuario = sc.nextLine();

	            System.out.print("Ingrese la contraseña: ");
	            String password = sc.nextLine();

	            // Creación de la entidad Credenciales vinculado al Cliente
	            Credenciales credenciales = new Credenciales();
	            credenciales.setUsuario(usuario);
	            credenciales.setPassword(password);
	            credenciales.setCliente(clienteGuardado);

	            // Guardar Credenciales
	            Credenciales credencialesGuardadas = S_credenciales.guardarCredenciales(credenciales);

	            if (credencialesGuardadas != null && credencialesGuardadas.getId() > 0) {
	                System.out.println("Cliente y credenciales registradas correctamente.");
	            } else {
	                System.err.println("Error al registrar las credenciales.");
	            }
	        } else {
	            System.err.println("Error al registrar al cliente.");
	        }
	    } catch (Exception e) {
	        System.err.println("Ocurrió un error: " + e.getMessage());
	    }
	}

}