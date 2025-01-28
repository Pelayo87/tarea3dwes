package com.pelayora.tarea3dwes.controlador;

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
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes("nombreUsuario")
public class EjemplarController {

    @Autowired
    private ServicioEjemplar S_ejemplar;

    @Autowired
    private ServicioPlanta S_planta;

    @GetMapping("/ejemplares-admin")
    public String EjemplaresAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Gestión de ejemplares (Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("ejemplares", S_ejemplar.obtenerTodosLosEjemplares());
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "ejemplares-admin";
    }

    @PostMapping("/ejemplares-admin")
    public String añadirEjemplar(@RequestParam("planta") String codigoPlanta,
            @ModelAttribute("nombreUsuario") String nombreUsuario,
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

        return "redirect:/ejemplares-admin";
    }

    @PostMapping("/ejemplares-admin/eliminar")
    public String eliminarEjemplar(@RequestParam("id_ejemplar") Long id_ejemplar, 
                                @ModelAttribute("nombreUsuario") String nombreUsuario,
                                Model model) {
        Optional<Ejemplar> ejemplar = S_ejemplar.obtenerEjemplarPorId(id_ejemplar);
        if (!ejemplar.isPresent()) {
            model.addAttribute("error", "Ejemplar no encontrado");
            return "redirect:/ejemplares-admin";
        }

        S_ejemplar.eliminarEjemplar(id_ejemplar);
        return "redirect:/ejemplares-admin";
    }

}
