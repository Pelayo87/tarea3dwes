package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Fitosanitario;
import com.pelayora.tarea3dwes.modelo.Historial;
import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioHistorial;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;

/**
 * Controlador para la gestión de fitosanitarios en la aplicación.
 */
@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class FitosanitariosController {
	
	@Autowired
    private ServicioEjemplar S_ejemplar;
    
	@Autowired
    private ServicioFitosanitario S_fitosanitario;
	
	@Autowired
    private ServicioHistorial S_historial;
	
	@Autowired
    private ServicioMensaje S_mensaje;
	

	/**
     * Muestra la vista de gestión de fitosanitarios.
     * @param nombreComun Nombre común del fitosanitario (opcional).
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param model Modelo para la vista.
     * @return Vista "fitosanitarios-admin".
     */
	@GetMapping("/gestion-fitosanitarios")
	public String FitosanitariosAdmin(@RequestParam(value = "nombreComun", required = false) String nombreComun,
			@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {

		model.addAttribute("mensaje", "Gestión de fitosanitarios (Usuario administrador)");
		model.addAttribute("UsuarioActual", nombreUsuario);
		model.addAttribute("ejemplares", S_ejemplar.obtenerTodosLosEjemplares());
		model.addAttribute("fitosanitarios", S_fitosanitario.obtenerTodosLosFitosanitarios());

		return "gestion-fitosanitarios";
	}

	/**
     * Aplica un fitosanitario a un ejemplar y guarda el historial correspondiente.
     * @param idEjemplar ID del ejemplar.
     * @param idFitosanitario ID del fitosanitario.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param id_persona ID de la persona que realiza la acción.
     * @param model Modelo para la vista.
     * @return Redirección a "gestion-fitosanitarios".
     */
	@PostMapping("/gestion-fitosanitarios")
	public String aplicarFitosanitario(@RequestParam("ejemplar") long idEjemplar,
	        @RequestParam("fitosanitario") long idFitosanitario,
	        @ModelAttribute("nombreUsuario") String nombreUsuario,
	        @ModelAttribute("id_Persona") long id_persona,
	        Model model) {

	    Optional<Ejemplar> ejemplar = S_ejemplar.obtenerEjemplarPorId(idEjemplar);
	    Optional<Fitosanitario> fitosanitario = S_fitosanitario.obtenerFitosanitariosPorId(idFitosanitario);

	    if (!ejemplar.isPresent()) {
	        model.addAttribute("error", "Ejemplar no encontrado");
	        return "redirect:/gestion-fitosanitarios";
	    }
	    
	    if (!fitosanitario.isPresent()) {
	        model.addAttribute("error", "Fitosanitario no encontrado");
	        return "redirect:/gestion-fitosanitarios";
	    }
	    
	    // Añado el ejemplar al fitosanitario y lo aplico en la BD
	    fitosanitario.get().getEjemplares().add(ejemplar.get());
	    S_fitosanitario.aplicarFitosanitarioAejemplar(fitosanitario.get());
	    
	    String nombreEjemplar = ejemplar.get().getNombre();

	    System.out.println("El fitosanitario " + fitosanitario.get().getNombre() +
	                       " ha sido aplicado al ejemplar " + nombreEjemplar + ".");
	    
	    String nh = "NH_" + nombreEjemplar;
	    LocalDate actualizado = LocalDate.now();
	    
	    Historial historialEjemplar = new Historial();
	    historialEjemplar.setNh(nh);
	    historialEjemplar.setActualizado(actualizado);
	    historialEjemplar.setEjemplar(ejemplar.get());
	    
	    S_historial.guardarHistorial(historialEjemplar);
	    
	    Mensaje mensajeHistorial = new Mensaje();
        mensajeHistorial.setFechahora(new Date()); // Fecha actual
        mensajeHistorial.setMensaje("Se aplica " +  fitosanitario.get().getNombre() + " por " + nombreUsuario
                                     + " a fecha " + new Date());
        
        mensajeHistorial.setEjemplar(ejemplar.get());
        mensajeHistorial.setHistorial(historialEjemplar);
        Persona personaActual = new Persona();
        personaActual.setId(id_persona);
        mensajeHistorial.setPersona(personaActual);
        
        // Guardo el mensaje
        S_mensaje.guardarMensaje(mensajeHistorial);

	    return "redirect:/gestion-fitosanitarios";
	}
}
