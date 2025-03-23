package com.pelayora.tarea3dwes.controlador;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


/**
 * Controlador para la gestión de plantas en el vivero.
 * Proporciona funcionalidades para agregar, modificar y eliminar plantas.
 */
@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class PlantaController {

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioEjemplar S_ejemplar;
    
   

    /**
     * Muestra la página de administración de plantas.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param model Modelo de la vista.
     * @return Vista de administración de plantas.
     */
    @GetMapping("/plantas-admin")
    public String PlantasAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Gestión de plantas (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        return "plantas-admin";
    }

    /**
     * Muestra el formulario para añadir una nueva planta.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param model Modelo de la vista.
     * @return Vista para añadir plantas.
     */
	@GetMapping("/plantas-adminAnadir")
	public String PlantasAdminAnadir(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
		try {
			model.addAttribute("UsuarioActual", nombreUsuario);
			model.addAttribute("planta", new Planta());
		} catch (Exception e) {
			model.addAttribute("error", "Error al obtener los datos de la planta: " + e.getMessage());
			return "redirect:/plantas-admin";
		}
		return "plantas-adminAñadir";
	}

    /**
     * Guarda una nueva planta en la base de datos.
     * @param planta Planta a guardar.
     * @param resultado Resultado de validación.
     * @param redirectAttributes Atributos para redireccionar mensajes.
     * @return Redirección a la vista de administración de plantas.
     */
    @PostMapping("/plantas-adminAnadir")
    public String guardarPlanta(@ModelAttribute Planta planta, BindingResult resultado, Model model) {
        String codigo = planta.getCodigo();
        String nombreComun = planta.getNombreComun();
        String nombreCientifico = planta.getNombreCientifico();

        // Validaciones
        if (codigo == null || !codigo.matches("^[A-Za-z]+$")) {
            resultado.rejectValue("codigo", "codigo.invalid", "El código no debe contener espacios, dígitos ni caracteres especiales.");
        }

        if (codigo != null) {
            String codigoNormalizado = Normalizer.normalize(codigo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            planta.setCodigo(codigoNormalizado.toUpperCase());
        }

        if (S_planta.existPlantaPorCodigo(planta.getCodigo())) {
            resultado.rejectValue("codigo", "codigo.exists", "Ya hay una planta con ese código.");
        }

        if (nombreComun == null || !nombreComun.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
            resultado.rejectValue("nombreComun", "nombreComun.invalid", "El nombre común solo puede contener letras y espacios.");
        }

        if (nombreCientifico == null || !nombreCientifico.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
            resultado.rejectValue("nombreCientifico", "nombreCientifico.invalid", "El nombre científico solo puede contener letras y espacios.");
        }

        if (resultado.hasErrors()) {
            return "plantas-adminAñadir";
        }

        try {
            planta.setCodigo(codigo.toUpperCase());
            planta.setNombreComun(nombreComun.toUpperCase());
            planta.setNombreCientifico(nombreCientifico.toUpperCase());
            planta.setPrecio(10);
            S_planta.guardarPlanta(planta);
            model.addAttribute("mensaje", "✅ Planta añadida con éxito.");
        } catch (Exception e) {
            model.addAttribute("error", "❌ Error al añadir la planta: " + e.getMessage());
        }

        return "plantas-adminAñadir";
    }
    
    @GetMapping("/plantas-adminModificar")
    public String PlantasAdminModificar(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        try {
            model.addAttribute("UsuarioActual", nombreUsuario);
            model.addAttribute("plantas", S_planta.listarPlantas());
            model.addAttribute("planta", new Planta());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los datos de la planta: " + e.getMessage());
            return "redirect:/plantas-admin";
        }
        return "plantas-adminModificar";
    }

	@PostMapping("/plantas-adminModificar")
	public String modificarPlanta(@RequestParam("planta") String codigoPlanta, @ModelAttribute Planta planta,
			                      BindingResult resultado, Model model) {
		
		String nombreComun = planta.getNombreComun();
		String nombreCientifico = planta.getNombreCientifico();
		model.addAttribute("plantas", S_planta.listarPlantas());

		Optional<Planta> plantaExistente = S_planta.buscarPlantaPorId(codigoPlanta);

		// Validaciones
		if (!plantaExistente.isPresent()) {
			resultado.rejectValue("codigo", "codigo.invalid", "Planta con el código " + codigoPlanta + " no encontrada.");
		}

		if (nombreComun == null || !nombreComun.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
			resultado.rejectValue("nombreComun", "nombreComun.invalid", "El nombre común solo puede contener letras y espacios.");
		}

		if (nombreCientifico == null || !nombreCientifico.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$")) {
			resultado.rejectValue("nombreCientifico", "nombreCientifico.invalid", "El nombre científico solo puede contener letras y espacios.");
		}

		if (resultado.hasErrors()) {
			return "plantas-adminModificar";
		}
		
		try {

			Planta plantaModificada = plantaExistente.get();
			plantaModificada.setNombreComun(nombreComun.toUpperCase());
			plantaModificada.setNombreCientifico(nombreCientifico.toUpperCase());

			S_planta.modificarPlanta(plantaModificada);
			model.addAttribute("mensaje", "✅ Planta modificada con éxito.");

		} catch (Exception e) {
			model.addAttribute("error", "❌ Error al modificar la planta: " + e.getMessage());
		}
		
		return "plantas-adminModificar";
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

    /**
     * Elimina una planta de la base de datos.
     * @param codigo Código de la planta a eliminar.
     * @param model Modelo de la vista.
     * @return Redirección a la vista de administración de plantas.
     */
    @PostMapping("/plantas-adminEliminar")
    public String eliminarPlanta(@RequestParam("codigo") String codigo, Model model) {
        try {
            Optional<Planta> planta_codigo = S_planta.buscarPlantaPorId(codigo);
            List<Ejemplar> ejemplares = S_ejemplar.obtenerEjemplarPorPlanta(codigo);

            if (!planta_codigo.isPresent()) {
                model.addAttribute("error", "Planta con el código " + codigo + " no encontrada.");
                System.err.println("Planta no encontrada.");
                return "plantas-adminEliminar";
            }
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
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar la planta: " + e.getMessage());
            System.err.println("Error al eliminar la planta: " + e.getMessage());
            return "redirect:/plantas-adminEliminar";
        }
    }
}