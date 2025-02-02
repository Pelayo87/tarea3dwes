package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes("nombreUsuario")
public class ViveroController {
	
	@Autowired
	private ServicioPlanta S_planta;
	
	@Autowired
	private ServicioEjemplar S_ejemplar;
	
	@Autowired
	private ServicioFitosanitario S_fitosanitario;
	
	@Autowired
	private ServicioMensaje S_mensaje;
	
	@GetMapping("/inicio")
    public String inicioVivero(Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero");
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio";
    }

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
	
	@GetMapping("/inicio-cliente")
    public String inicioViveroCliente(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario cliente)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-cliente";
    }
	
	/*// Método para obtener la primera letra
    private String obtenerPrimeraLetra(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return String.valueOf(nombre.charAt(0)).toUpperCase();
        }
        return "";
    }	*/
}
