package com.pelayora.tarea3dwes.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes("nombreUsuario")
public class PlantaController {

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioEjemplar S_ejemplar;

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
            System.out.println("Planta añadida con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al añadir la planta: " + e.getMessage());
            System.err.println("Error al añadir la planta: " + e.getMessage());
        }
        return "redirect:/plantas-admin";
    }

    @GetMapping("/plantas-adminModificar")
    public String PlantasAdminModificar(@ModelAttribute("nombreUsuario") String nombreUsuario, 
                                         Model model) {
        try {
            model.addAttribute("mensaje", "Modificar planta (Usuario administrador)");
            model.addAttribute("UsuarioActual", nombreUsuario);
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los datos de la planta: " + e.getMessage());
            System.err.println("Error al obtener los datos de la planta: " + e.getMessage());
            return "redirect:/plantas-admin";
        }
        return "plantas-adminModificar";
    }

    @PostMapping("/plantas-adminModificar")
    public String modificarPlanta(@RequestParam("codigo") String codigo,
    		                      @ModelAttribute Planta planta, RedirectAttributes redirectAttributes, Model model) {
        try {
        	Optional<Planta> planta_codigo = S_planta.buscarPlantaPorId(codigo);
        	model.addAttribute("planta_codigo", planta_codigo);
        	if (planta_codigo.isPresent()) {
                S_planta.modificarPlanta(planta);
                redirectAttributes.addFlashAttribute("mensaje", "Planta modificada con éxito.");
                System.out.println("Planta modificada con éxito.");
        	} else {
                redirectAttributes.addFlashAttribute("error", "Planta no encontrada.");
                System.err.println("Planta no encontrada.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al modificar la planta: " + e.getMessage());
        }
        return "redirect:/plantas-admin";
    }
    
    @GetMapping("/plantas-adminEliminar")
    public String PlantasAdminEliminar(@ModelAttribute("nombreUsuario") String nombreUsuario, 
                                       Model model) {
        try {
            model.addAttribute("mensaje", "Borrar planta (Usuario administrador)");
            model.addAttribute("UsuarioActual", nombreUsuario);
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los datos de la planta: " + e.getMessage());
            System.err.println("Error al obtener los datos de la planta: " + e.getMessage());
            return "redirect:/plantas-admin";
        }
        return "plantas-adminEliminar";
    }

    @PostMapping("/plantas-adminEliminar")
    public String eliminarPlanta(@RequestParam("codigo") String codigo, Model model) {
        try {
            Optional<Planta> planta_codigo = S_planta.buscarPlantaPorId(codigo);
            if (planta_codigo.isPresent()) {
                List<Ejemplar> ejemplares = S_ejemplar.obtenerEjemplarPorPlanta(codigo);
                if (!ejemplares.isEmpty()) {
                    model.addAttribute("error", "La planta tiene ejemplares asociados y no puede ser eliminada.");
                    System.err.println("La planta tiene ejemplares asociados y no puede ser eliminada.");
                    return "plantas-adminEliminar";
                } else {
                    S_planta.eliminarPlanta(codigo);
                    model.addAttribute("mensaje", "Planta eliminada con éxito.");
                    System.out.println("Planta eliminada.");
                    return "plantas-admin";
                }
            } else {
                model.addAttribute("error", "Planta no encontrada.");
                System.err.println("Planta no encontrada.");
                return "plantas-adminEliminar";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar la planta: " + e.getMessage());
            System.err.println("Error al eliminar la planta: " + e.getMessage());
            return "plantas-adminEliminar";
        }
    }
    

}