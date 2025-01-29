package com.pelayora.tarea3dwes.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona"})
public class MensajeController {

    @Autowired
    private ServicioEjemplar S_ejemplar;
    
    @Autowired
    private ServicioMensaje S_mensaje;

    @GetMapping("/mensajes-admin")
    public String MensajesAdmin(
            @RequestParam(value = "nombre", required = false) String nombre,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
            Model model) {

        model.addAttribute("mensaje", "Gestión de ejemplares (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);

        if (nombre != null && !nombre.isEmpty()) {
            List<Mensaje> mensajesFiltrados = S_mensaje.obtenerMensajePorNombreEjemplar(nombre);
            model.addAttribute("mensajes", mensajesFiltrados);
            model.addAttribute("mensaje", "Filtrado por ejemplar: " + nombre);
        } else {
            model.addAttribute("mensajes", S_mensaje.listarMensajes());
        }
        return "mensajes-admin";
    }
    
}
