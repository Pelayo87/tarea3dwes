package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

/**
 * Controlador para gestionar los mensajes y anotaciones en el sistema.
 */
@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class MensajeController {

    @Autowired
    private ServicioPlanta S_planta;

    @Autowired
    private ServicioEjemplar S_ejemplar;
    
    @Autowired
    private ServicioMensaje S_mensaje;

    @Autowired
    private ServicioPersona S_persona;

    /**
     * Muestra la vista de administración de mensajes con filtros opcionales.
     * 
     * @param nombre Nombre de la persona asociada al mensaje.
     * @param fechaInicio Fecha de inicio del filtro.
     * @param fechaFin Fecha de fin del filtro.
     * @param planta Tipo de planta asociada.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param model Modelo de datos para la vista.
     * @return Nombre de la vista "gestion-mensajes".
     */
    @GetMapping("/gestion-mensajes")
    public String MensajesAdmin(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(value = "planta", required = false) String planta,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            Model model) {

        model.addAttribute("mensaje", "Gestión de ejemplares (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("plantas", S_planta.listarPlantas());
        model.addAttribute("personas", S_persona.listarPersonas());
        model.addAttribute("ejemplares", S_ejemplar.obtenerTodosLosEjemplares());

        List<Mensaje> mensajesFiltrados = S_mensaje.listarMensajes();

        if (nombre != null && !nombre.isEmpty()) {
            mensajesFiltrados = S_mensaje.obtenerMensajePorNombrePersona(nombre);
            model.addAttribute("mensaje", "Filtrado por persona: " + nombre);
        }

        if (fechaInicio != null && fechaFin != null) {
            if (fechaInicio.isAfter(fechaFin)) {
                model.addAttribute("error", "La fecha de inicio no puede ser posterior a la fecha de fin.");
            } else {
                LocalDateTime inicio = fechaInicio.atStartOfDay();
                LocalDateTime fin = fechaFin.atTime(23, 59, 59);

                mensajesFiltrados = mensajesFiltrados.stream()
                        .filter(m -> {
                            LocalDateTime fechaMensaje = m.getFechahora().toInstant().atZone(ZoneId.systemDefault())
                                    .toLocalDateTime();
                            return !fechaMensaje.isBefore(inicio) && !fechaMensaje.isAfter(fin);
                        })
                        .collect(Collectors.toList());
                model.addAttribute("mensaje", "Filtrado por fechas: desde " + fechaInicio + " hasta " + fechaFin);
            }
        }

        if (planta != null && !planta.isEmpty()) {
            mensajesFiltrados = S_mensaje.obtenerMensajePorTipoPlanta(planta);
            model.addAttribute("mensaje", "Filtrado por tipo de planta: " + planta);
        }

        model.addAttribute("mensajes", mensajesFiltrados);
        return "gestion-mensajes";
    }
    
    /**
     * Registra una anotación en un ejemplar.
     * 
     * @param ejemplarId ID del ejemplar asociado a la anotación.
     * @param mensajeTexto Contenido del mensaje.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param id_Persona ID de la persona asociada.
     * @param model Modelo de datos para la vista.
     * @return Redirección a la vista "gestion-mensajes".
     */
    @PostMapping("/gestion-mensajes")
    public String realizarAnotacion(@RequestParam("ejemplar") Long ejemplarId,
                                    @RequestParam("mensaje") String mensajeTexto,
                                    @ModelAttribute("nombreUsuario") String nombreUsuario,
                                    @ModelAttribute("id_Persona") long id_Persona,
                                    Model model) {

        boolean hayErrores = false;

        if (mensajeTexto == null || mensajeTexto.trim().isEmpty()) {
            model.addAttribute("mensajeError", "La anotación no puede estar vacía.");
            System.err.println("La anotación no puede estar vacía.");
            hayErrores = true;
        }

        Optional<Ejemplar> ejemplar = S_ejemplar.obtenerEjemplarPorId(ejemplarId);
        if (!ejemplar.isPresent()) {
            model.addAttribute("ejemplarError", "El ejemplar seleccionado no es válido.");
            System.err.println("El ejemplar seleccionado no es válido.");
            hayErrores = true;
        }

        if (hayErrores) {
            model.addAttribute("ejemplares", S_ejemplar.obtenerTodosLosEjemplares());
            return "gestion-mensajes";
        }

        Optional<Persona> persona = S_persona.buscarPorId(id_Persona);
        if (!persona.isPresent()) {
            model.addAttribute("error", "No se pudo encontrar la persona asociada al usuario.");
            System.err.println("No se pudo encontrar la persona asociada al usuario.");
            return "gestion-mensajes";
        }
        Persona personaMensaje = persona.get();

        Mensaje anotacion = new Mensaje();
        anotacion.setFechahora(new Date());
        anotacion.setMensaje(mensajeTexto);
        anotacion.setEjemplar(ejemplar.get());
        anotacion.setPersona(personaMensaje);
        S_mensaje.guardarMensaje(anotacion);

        model.addAttribute("success", "Anotación registrada con éxito para el ejemplar: " + ejemplar.get().getNombre());
        return "redirect:/gestion-mensajes";
    }
}
