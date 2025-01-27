package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes("nombreUsuario")
public class PlantaController {

    @Autowired
    private ServicioPlanta S_planta;

    @GetMapping("/plantas-admin")
    public String PlantasAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Gestión de plantas (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "plantas-admin";
    }

    @GetMapping("/plantas-adminAnadir")
    public String PlantasAdminAnadir(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Añadir planta (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "plantas-adminAñadir";
    }

    @PostMapping("/plantas-adminAnadir")
    public String guardarPlanta(@ModelAttribute Planta planta, RedirectAttributes redirectAttributes) {
        try {
            S_planta.guardarPlanta(planta);
            redirectAttributes.addFlashAttribute("mensaje", "Planta añadida con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al añadir la planta: " + e.getMessage());
        }
        return "redirect:/plantas-admin";
    }

    @GetMapping("/plantas-adminModificar")
    public String PlantasAdminModificar(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Modificar planta (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "plantas-adminModificar";
    }

    @PostMapping("/plantas-adminModificar")
    public String modificarPlanta(@ModelAttribute Planta planta, RedirectAttributes redirectAttributes) {
        try {
            S_planta.guardarPlanta(planta);
            redirectAttributes.addFlashAttribute("mensaje", "Planta añadida con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al añadir la planta: " + e.getMessage());
        }
        return "redirect:/plantas-admin";
    }

}