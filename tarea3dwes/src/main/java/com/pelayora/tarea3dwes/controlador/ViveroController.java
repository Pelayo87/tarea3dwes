package com.pelayora.tarea3dwes.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nombreUsuario")
public class ViveroController {
	
	@GetMapping({"/", "/inicio"})
	public String inicio(@ModelAttribute("nombreUsuario") String nombreUsuario, Model modelo) {
		
		modelo.addAttribute("UsuarioActual", nombreUsuario);
        modelo.addAttribute("InicialUsuario", obtenerPrimeraLetra(nombreUsuario));
        return "inicio";
	}
	
	// Método para obtener la primera letra
    private String obtenerPrimeraLetra(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return String.valueOf(nombre.charAt(0)).toUpperCase();
        }
        return "";
    }
	
}
