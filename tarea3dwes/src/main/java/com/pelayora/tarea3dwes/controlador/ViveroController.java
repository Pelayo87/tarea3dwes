package com.pelayora.tarea3dwes.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nombreUsuario")
public class ViveroController {
	
	@GetMapping("/anadirpersonal")
	public String añadirPersonal(Model model) {
	    model.addAttribute("mensaje", "Añadir Personal");
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
