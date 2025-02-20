package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;


/**
 * Controlador para la gestión del vivero.
 * Maneja las rutas relacionadas con la visualización de la información del vivero
 * para diferentes tipos de usuarios: administrador, personal y clientes.
 */

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class ViveroController {
    
    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioEjemplar S_ejemplar;
    
    @Autowired
    private ServicioFitosanitario S_fitosanitario;
    
    @Autowired
    private ServicioMensaje S_mensaje;
	
	@Autowired
	private ServicioCliente S_cliente;
	
	
    /**
     * Maneja la página de inicio del vivero.
     * 
     * @param model Modelo de Spring para pasar atributos a la vista.
     * @return La vista "inicio".
     */
	@GetMapping("/inicio")
    public String inicioVivero(Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero");
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio";
    }
    
    /**
     * Maneja la página de inicio para administradores.
     * 
     * @param nombreUsuario Nombre del usuario administrador.
     * @param model Modelo de Spring para pasar atributos a la vista.
     * @return La vista "inicio-admin".
     */
	@GetMapping("/inicio-admin")
    public String inicioViveroAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        long totalPlantas = S_planta.contadorPlantas();
        model.addAttribute("totalPlantas", totalPlantas);
        long totalEjemplares = S_ejemplar.contadorEjemplares();
        model.addAttribute("totalEjemplares", totalEjemplares);
        long totalFitosanitarios = S_fitosanitario.contadorFitosanitarios();
        model.addAttribute("totalFitosanitarios", totalFitosanitarios);
        long totalMensajes = S_mensaje.contadorMensajes();
        model.addAttribute("totalMensajes", totalMensajes);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-admin";
    }
    
    /**
     * Maneja la página de inicio para personal del vivero.
     * 
     * @param nombreUsuario Nombre del usuario personal.
     * @param model Modelo de Spring para pasar atributos a la vista.
     * @return La vista "inicio-personal".
     */
	@GetMapping("/inicio-personal")
    public String inicioViveroPersonal(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario personal)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        long totalPlantas = S_planta.contadorPlantas();
        model.addAttribute("totalPlantas", totalPlantas);
        long totalEjemplares = S_ejemplar.contadorEjemplares();
        model.addAttribute("totalEjemplares", totalEjemplares);
        long totalFitosanitarios = S_fitosanitario.contadorFitosanitarios();
        model.addAttribute("totalFitosanitarios", totalFitosanitarios);
        long totalMensajes = S_mensaje.contadorMensajes();
        model.addAttribute("totalMensajes", totalMensajes);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-personal";
    }
}

