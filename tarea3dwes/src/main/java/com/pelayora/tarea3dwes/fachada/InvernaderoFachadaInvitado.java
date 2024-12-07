package com.pelayora.tarea3dwes.fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pelayora.tarea3dwes.modelo.*;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;
import com.pelayora.tarea3dwes.util.*;

@Component
public class InvernaderoFachadaInvitado {
	Scanner sc = new Scanner(System.in);
    String nombreusuario;
    Persona usuarioActual;    

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioCredenciales S_credenciales;
       
    public void invitado() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - VER PLANTAS");
            System.out.println("\t\t\t\t2 - LOGIN");
            System.out.println("\t\t\t\t3 - SALIR DEL PROGRAMA");
            System.out.println("\t\t\t\t4 - VOLVER ATRAS");

            opcion = Utilidades.obtenerOpcionUsuario(4);

            switch (opcion) {
                case 1: {
                    mostrarPlantas();
                    break;
                }
                case 2: {
                    InvernaderoFachadaPrincipal invernaderoFachadaPrincipal = new InvernaderoFachadaPrincipal();
					invernaderoFachadaPrincipal.login();
                    break;
                }
                case 3: {
                	Utilidades.salirdelprograma();
                }
            }
        } while (opcion != 3);
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

}
