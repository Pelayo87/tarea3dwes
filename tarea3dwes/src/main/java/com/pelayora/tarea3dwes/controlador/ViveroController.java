package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes("nombreUsuario")
public class ViveroController {
	
	@Autowired
	private ServicioPlanta S_planta;
	
	@GetMapping("/inicio")
    public String inicioVivero(Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero");
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio";
    }
	
	@GetMapping("/iniciosesion-registrarse")
	public String mostrarFormulario(@RequestParam(value = "action", required = false) String action, Model model) {
	    if ("registro".equals(action)) {
	        model.addAttribute("mostrarRegistro", true);
	    } else {
	        model.addAttribute("mostrarRegistro", false);
	    }
	    return "iniciosesion-registrarse";
	}

    @GetMapping("/inicio-admin")
    public String inicioViveroAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "inicio-admin";
    }
	
	@GetMapping("/inicio-cliente")
    public String inicioViveroCliente(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario cliente)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-cliente";
    }
	
	@GetMapping("/anadirpersonal")
    public String anadirPersonal(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Añadir personal al vivero");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "añadirpersonal";
    }
	
	// Método para obtener la primera letra
    private String obtenerPrimeraLetra(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return String.valueOf(nombre.charAt(0)).toUpperCase();
        }
        return "";
    }
	
}
