package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String iniciarSesion_Registrarse(Model model) {
	    model.addAttribute("mensaje", "Iniciar sesión / Registrarse");
	    return "iniciosesion-registrarse";
	}
	
	// Método para obtener la primera letra
    private String obtenerPrimeraLetra(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return String.valueOf(nombre.charAt(0)).toUpperCase();
        }
        return "";
    }
	
}
