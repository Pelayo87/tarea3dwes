package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class ClienteController { 
	
	 @Autowired
	 private ServicioPlanta S_planta;
	    
	 @Autowired
	 private ServicioEjemplar S_ejemplar;
	    	
     @Autowired
     private ServicioCliente S_cliente;
	
	LocalDate Fechahoy = LocalDate.now();
	String fechahoyFormateada = Fechahoy.getDayOfMonth() + " de " +
			 Fechahoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + " de " +
			 Fechahoy.getYear();
	
    /**
     * Maneja la página de inicio para clientes.
     * 
     * @param nombreUsuario Nombre del usuario cliente.
     * @param model Modelo de Spring para pasar atributos a la vista.
     * @return La vista "inicio-cliente".
     */
	
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
            List<Planta> plantas = S_planta.listarPlantas();
            model.addAttribute("plantas", plantas);
            Map<String, Integer> Listadisponibles = new HashMap<>();
            for (Planta p : plantas) {
                int disponibles = S_ejemplar.contarEjemplaresDisponibles(p.getCodigo());
                Listadisponibles.put(p.getCodigo(), disponibles);
            }
            model.addAttribute("disponibles", Listadisponibles);
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
	
	@GetMapping("/factura")
    public String facturaCompraCliente(@ModelAttribute("nombreUsuario") String nombreUsuario,
                                      @ModelAttribute("id_Cliente") Long id_Cliente,
                                      @ModelAttribute("UsuarioCliente") Cliente Usuario,
                                      Model model) {
		model.addAttribute("mensaje", "Factura del cliente");
		model.addAttribute("UsuarioActual", nombreUsuario);
		model.addAttribute("id_Cliente", id_Cliente);
		model.addAttribute("UsuarioCliente", Usuario);
		model.addAttribute("fechaHoy", fechahoyFormateada);
		String direccionCliente= Usuario.getDireccionEnvio();
		String nifnieCliente=Usuario.getNif_nie();
		String emailCliente=Usuario.getEmail();
		model.addAttribute("direccionCliente", direccionCliente);
		model.addAttribute("nifnieCliente", nifnieCliente);
		model.addAttribute("emailCliente", emailCliente);
		return "factura";
	}
	
	@GetMapping("/carrito-compra")
    public String carritoCompraCliente(@ModelAttribute("nombreUsuario") String nombreUsuario,
                                      @ModelAttribute("id_Cliente") Long id_Cliente,
                                      Model model) {
		model.addAttribute("mensaje", "Factura del cliente");
		model.addAttribute("UsuarioActual", nombreUsuario);
		model.addAttribute("id_Cliente", id_Cliente);
		return "carrito-compra";
	}
	
	@PostMapping("/carrito-compra")
	public String addToCart(@RequestParam("CodigoPlanta") String codigoPlanta,
	                        @RequestParam("cantidad") int cantidad,
	                        Model model) {
	    int disponibles = S_ejemplar.contarEjemplaresDisponibles(codigoPlanta);

	    if (cantidad > disponibles) {
	        model.addAttribute("error", "No hay suficientes ejemplares disponibles para esa planta.");
	        return "redirect:/inicio-cliente";
	    }

	    return "redirect:/carrito-compra";
	}

}
