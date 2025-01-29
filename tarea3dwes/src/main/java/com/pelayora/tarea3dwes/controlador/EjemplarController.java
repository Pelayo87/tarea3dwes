package com.pelayora.tarea3dwes.controlador;

import java.util.Date;
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
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona"})
public class EjemplarController {

    @Autowired
    private ServicioEjemplar S_ejemplar;

    @Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioMensaje S_mensaje;

    @GetMapping("/ejemplares-admin")
    public String EjemplaresAdmin(
            @RequestParam(value = "nombreComun", required = false) String nombreComun,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            Model model) {

        model.addAttribute("mensaje", "Gestión de ejemplares (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);

        if (nombreComun != null && !nombreComun.isEmpty()) {
            List<Ejemplar> ejemplaresFiltrados = S_ejemplar.obtenerEjemplarPorNombrePlanta(nombreComun);
            model.addAttribute("ejemplares", ejemplaresFiltrados);
            model.addAttribute("mensaje", "Filtrado por planta: " + nombreComun);
        } else {
            model.addAttribute("ejemplares", S_ejemplar.obtenerTodosLosEjemplares());
        }

        model.addAttribute("plantas", S_planta.listarPlantas());
        return "ejemplares-admin";
    }


    @PostMapping("/ejemplares-admin")
    public String añadirEjemplar(@RequestParam("planta") String codigoPlanta,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            @ModelAttribute("id_Persona") long id_persona,
            Model model) {

        Optional<Planta> plantaOpt = S_planta.buscarPlantaPorId(codigoPlanta);
        if (!plantaOpt.isPresent()) {
            model.addAttribute("error", "Planta no encontrada");
            return "redirect:/ejemplares-admin";
        }

        Planta planta = plantaOpt.get();
        Ejemplar nuevoEjemplar = new Ejemplar();
        nuevoEjemplar.setPlanta(planta);
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

        return "redirect:/ejemplares-admin";
    }
    
    @PostMapping("/ejemplares-admin/modificar")
    public String modificarEjemplar(@RequestParam("planta") String codigoPlanta,
            @RequestParam("id_ejemplar") Long id_ejemplar,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            @ModelAttribute("id_Persona") long id_persona,
            Model model) {

        Optional<Ejemplar> ejemplarOpt = S_ejemplar.obtenerEjemplarPorId(id_ejemplar);
        Optional<Planta> plantaOpt = S_planta.buscarPlantaPorId(codigoPlanta);

        if (!ejemplarOpt.isPresent()) {
            model.addAttribute("error", "Ejemplar no encontrado");
            return "redirect:/ejemplares-admin";
        }
        
        if (!plantaOpt.isPresent()) {
            model.addAttribute("error", "Planta no encontrada");
            return "redirect:/ejemplares-admin";
        }

        Ejemplar ejemplar = ejemplarOpt.get();
        Planta planta = plantaOpt.get();
        
        ejemplar.setPlanta(planta);

        String nombreEjemplar = planta.getCodigo() + "_" + id_ejemplar;
        ejemplar.setNombre(nombreEjemplar);

        S_ejemplar.modificarEjemplar(ejemplar);

        // Creo un mensaje de registro
        Mensaje mensajeInicial = new Mensaje();
        mensajeInicial.setFechahora(new Date());
        mensajeInicial.setMensaje("Ejemplar actualizado por " + nombreUsuario + " el " + new Date());
        mensajeInicial.setEjemplar(ejemplar);

        Persona personaActual = new Persona();
        personaActual.setId(id_persona);
        mensajeInicial.setPersona(personaActual);

        S_mensaje.guardarMensaje(mensajeInicial);

        return "redirect:/ejemplares-admin";
    }


    @PostMapping("/ejemplares-admin/eliminar")
    public String eliminarEjemplar(@RequestParam("id_ejemplar") Long id_ejemplar, 
                                @ModelAttribute("nombreUsuario") String nombreUsuario,
                                @ModelAttribute("id_Persona") long id_persona,
                                Model model) {
        try {
    	Optional<Ejemplar> ejemplar = S_ejemplar.obtenerEjemplarPorId(id_ejemplar);
        if (!ejemplar.isPresent()) {
            model.addAttribute("error", "Ejemplar no encontrado");
            return "redirect:/ejemplares-admin";
        }

        S_ejemplar.eliminarEjemplar(id_ejemplar);
        
        Mensaje mensajeBorrarEjemplar = new Mensaje();
        mensajeBorrarEjemplar.setFechahora(new Date());
        mensajeBorrarEjemplar.setMensaje("Ejemplar: " + ejemplar + " eliminado por " + nombreUsuario + " el " + new Date());
        Persona personaActual = new Persona();
        personaActual.setId(id_persona);
        mensajeBorrarEjemplar.setPersona(personaActual);
        
        }catch(Exception e) {
        	model.addAttribute("error", "Error al borrar el ejemplar");
        	return "redirect:/ejemplares-admin";
        }

        return "redirect:/ejemplares-admin";
    }
    
    @PostMapping("/ejemplares-admin/filtrarportipoplanta")
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

        return "ejemplares-admin";
    }
}
