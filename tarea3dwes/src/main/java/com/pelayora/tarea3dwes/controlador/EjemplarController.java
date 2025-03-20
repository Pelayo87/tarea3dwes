package com.pelayora.tarea3dwes.controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

/**
 * Controlador para la gestión de ejemplares.
 */
@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class EjemplarController {

    @Autowired
    private ServicioEjemplar S_ejemplar;

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioMensaje S_mensaje;

    /**
     * Muestra la vista de gestión de ejemplares.
     * @param nombreComun Nombre común de la planta para filtrar.
     * @param nombre Nombre del ejemplar para filtrar.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param model Modelo para la vista.
     * @return Página de ejemplares.
     */
    @GetMapping("/gestion-ejemplares")
    public String EjemplaresAdmin(
            @RequestParam(value = "nombreComun", required = false) List<String> nombresComunes,
            @RequestParam(value = "nombre", required = false) String nombre,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            Model model) {

        model.addAttribute("mensaje", "Gestión de ejemplares");
        model.addAttribute("UsuarioActual", nombreUsuario);

        List<Ejemplar> ejemplares = (nombresComunes != null && !nombresComunes.isEmpty()) 
                ? S_ejemplar.obtenerEjemplaresPorNombresPlantas(nombresComunes)
                : S_ejemplar.obtenerTodosLosEjemplares();

        model.addAttribute("ejemplares", ejemplares);
        model.addAttribute("stockEjemplares", S_ejemplar.obtenerStockEjemplares());

        Map<Long, Integer> mensajesPorEjemplar = new HashMap<>();
        Map<Long, String> ultimoMensajePorEjemplar = new HashMap<>();

        for (Ejemplar ej : ejemplares) {
            int numMensajes = S_mensaje.contarMensajes(ej.getId());
            mensajesPorEjemplar.put(ej.getId(), numMensajes);

            S_mensaje.obtenerUltimoMensaje(ej.getId()).ifPresent(ultimoMensaje -> 
                ultimoMensajePorEjemplar.put(ej.getId(), ultimoMensaje.getFechahora().toString())
            );
        }

        model.addAttribute("mensajesPorEjemplar", mensajesPorEjemplar);
        model.addAttribute("ultimoMensajePorEjemplar", ultimoMensajePorEjemplar);
        
        if (nombre != null && !nombre.isEmpty()) {
            List<Mensaje> mensajesFiltrados = S_mensaje.obtenerMensajePorNombreEjemplar(nombre);
            model.addAttribute("mensajes", mensajesFiltrados);
            model.addAttribute("mensaje", "Filtrado por ejemplar: " + nombre);
        } else {
            model.addAttribute("mensajes", S_mensaje.listarMensajes());
        }

        model.addAttribute("plantas", S_planta.listarPlantas());
        return "gestion-ejemplares";
    }

    /**
     * Agrega un nuevo ejemplar al sistema.
     * @param codigoPlanta Código de la planta asociada.
     * @param nombreUsuario Nombre del usuario en sesión.
     * @param id_persona ID de la persona en sesión.
     * @param model Modelo para la vista.
     * @return Redirección a la página de ejemplares.
     */
    @PostMapping("/gestion-ejemplares")
    public String añadirEjemplar(@RequestParam("planta") String codigoPlanta,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            @ModelAttribute("id_Persona") long id_persona,
            Model model) {

        Optional<Planta> plantaOpt = S_planta.buscarPlantaPorId(codigoPlanta);
        if (!plantaOpt.isPresent()) {
            model.addAttribute("error", "Planta no encontrada");
            return "redirect:/gestion-ejemplares";
        }

        Planta planta = plantaOpt.get();
        Ejemplar nuevoEjemplar = new Ejemplar();
        nuevoEjemplar.setPlanta(planta);
        nuevoEjemplar.setDisponible(true);
        nuevoEjemplar = S_ejemplar.guardarEjemplar(nuevoEjemplar);

        String nombreEjemplar = planta.getCodigo() + "_" + nuevoEjemplar.getId();
        nuevoEjemplar.setNombre(nombreEjemplar);
        S_ejemplar.modificarEjemplar(nuevoEjemplar);
        
        Mensaje mensajeInicial = new Mensaje();
        mensajeInicial.setFechahora(new Date());
        mensajeInicial.setMensaje("Registro realizado por " + nombreUsuario + " el " + new Date());

        mensajeInicial.setEjemplar(nuevoEjemplar);
        Persona personaActual = new Persona();
        personaActual.setId(id_persona);
        mensajeInicial.setPersona(personaActual);

        // Guardo el mensaje
        S_mensaje.guardarMensaje(mensajeInicial);

        return "redirect:/gestion-ejemplares";
    }

    /**
     * Filtra los ejemplares por tipo de planta.
     *
     * @param tipoPlanta Tipo de planta por el cual se filtrarán los ejemplares.
     * @param nombreUsuario Nombre del usuario que realiza la acción.
     * @param model Modelo para agregar atributos a la vista.
     * @return Retorna la vista con los ejemplares filtrados.
     */
    @PostMapping("/gestion-ejemplares/filtrarportipoplanta")
    public String filtrarEjemplarPorTipoDePlanta(
            @RequestParam("tipoPlanta") String tipoPlanta,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            Model model) {

        List<Ejemplar> ejemplaresTipoPlanta = S_ejemplar.obtenerEjemplarPorPlanta(tipoPlanta);

        if (ejemplaresTipoPlanta.isEmpty()) {
            model.addAttribute("error", "No hay ejemplares para el tipo de planta seleccionado.");
        } else {
            model.addAttribute("ejemplares", ejemplaresTipoPlanta);
        }

        model.addAttribute("mensaje", "Filtrado por tipo de planta: " + tipoPlanta);
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("plantas", S_planta.listarPlantas());

        return "gestion-ejemplares";
    }
}
