package com.pelayora.tarea3dwes.controlador;

import java.util.Collections;
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

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente"})
public class ViveroController {
	
	@Autowired
	private ServicioCliente S_cliente;
	
	@Autowired
	private ServicioPlanta S_planta;
	
	@Autowired
	private ServicioEjemplar S_ejemplar;
	
	@Autowired
	private ServicioFitosanitario S_fitosanitario;
	
	@Autowired
	private ServicioMensaje S_mensaje;
	
	@GetMapping("/inicio")
    public String inicioVivero(Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero");
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio";
    }

    @GetMapping("/inicio-admin")
    public String inicioViveroAdmin(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario administrador)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        long totalPlantas = S_planta.contadorPlantas();
        model.addAttribute("totalPlantas", totalPlantas);
        long totalEjemplares = S_ejemplar.contadorEjemplares();
        model.addAttribute("totalEjemplares", totalEjemplares);
        long totalFitosanitarios = S_fitosanitario.contadorFitosanitarios();
        model.addAttribute("totalFitosanitarios", totalFitosanitarios);
        long totalMensajes = S_mensaje.contadorMensajes();
        model.addAttribute("totalMensajes", totalMensajes);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-admin";
    }
    
    @GetMapping("/inicio-personal")
    public String inicioViveroPersonal(@ModelAttribute("nombreUsuario") String nombreUsuario, Model model) {
        model.addAttribute("mensaje", "Página inicial del vivero(Usuario personal)");
        model.addAttribute("UsuarioActual", nombreUsuario);
        long totalPlantas = S_planta.contadorPlantas();
        model.addAttribute("totalPlantas", totalPlantas);
        long totalEjemplares = S_ejemplar.contadorEjemplares();
        model.addAttribute("totalEjemplares", totalEjemplares);
        long totalFitosanitarios = S_fitosanitario.contadorFitosanitarios();
        model.addAttribute("totalFitosanitarios", totalFitosanitarios);
        long totalMensajes = S_mensaje.contadorMensajes();
        model.addAttribute("totalMensajes", totalMensajes);
        model.addAttribute("plantas", S_planta.listarPlantas());
        return "inicio-personal";
    }
	
    @GetMapping("/inicio-cliente")
    public String inicioViveroCliente(@ModelAttribute("nombreUsuario") String nombreUsuario,
                                      @ModelAttribute("id_Cliente") Long id_Cliente,
                                      Model model) {
        try {
            Optional<Cliente> clienteActual = S_cliente.buscarPorId(id_Cliente);

            if (clienteActual.isPresent()) {
                Cliente cliente = clienteActual.get();
                List<Planta> plantasFavoritas = cliente.getPlantas();

                model.addAttribute("UsuarioActual", nombreUsuario);
                model.addAttribute("plantasFavoritas", plantasFavoritas);
            } else {
                model.addAttribute("plantasFavoritas", Collections.emptyList());
            }

            model.addAttribute("mensaje", "Página inicial del vivero (Usuario cliente)");
            model.addAttribute("plantas", S_planta.listarPlantas());
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar la información: " + e.getMessage());
        }

        return "inicio-cliente";
    }


	
	@PostMapping("/plantas-favoritas")
    public String anadirPlantaFavorita(@RequestParam("CodigoPlanta") String CodigoPlanta,
    		                           @ModelAttribute("id_Cliente") long id_Cliente,
                                       Model model) {

        Optional <Planta> Codigo_PlantaFavorita = S_planta.buscarPlantaPorId(CodigoPlanta);
        if (Codigo_PlantaFavorita.isEmpty()) {
            model.addAttribute("error", "Planta no encontrada.");
            return "redirect:/inicio-cliente";
        }
        Planta plantaSeleccionada = Codigo_PlantaFavorita.get();
        
        Optional<Cliente> Cliente = S_cliente.buscarPorId(id_Cliente);
        if (Cliente.isEmpty()) {
            model.addAttribute("error", "Cliente no encontrado.");
            return "redirect:/inicio-cliente";
        }
        Cliente cliente = Cliente.get();

        cliente.getPlantas().add(plantaSeleccionada);
        S_cliente.guardarPlantasFavoritasCliente(cliente);
        
        model.addAttribute("mensaje", "Has añadido la planta " + plantaSeleccionada.getNombreComun() + " a tus favoritas.");

        return "redirect:/inicio-cliente";
    }
	
	/*// Método para obtener la primera letra
    private String obtenerPrimeraLetra(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return String.valueOf(nombre.charAt(0)).toUpperCase();
        }
        return "";
    }	*/
}
