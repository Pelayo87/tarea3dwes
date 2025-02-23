package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;
import com.pelayora.tarea3dwes.modelo.Pedido;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPedido;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class ClienteController { 
	
	 @Autowired
	 private ServicioPlanta S_planta;
	    
	 @Autowired
	 private ServicioEjemplar S_ejemplar;
	    	
     @Autowired
     private ServicioCliente S_cliente;
     
     @Autowired
     private ServicioPedido S_pedido;
	
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
	public String facturaCompraCliente(@RequestParam("idPedido") Long idPedido,
	                                   @ModelAttribute("nombreUsuario") String nombreUsuario,
	                                   @ModelAttribute("id_Cliente") Long id_Cliente,
	                                   @ModelAttribute("UsuarioCliente") Cliente Usuario,
	                                   Model model) {

	    Optional<Pedido> pedido = S_pedido.buscarPedidoPorId(idPedido);
	    if (!pedido.isPresent()) {
	        model.addAttribute("error", "Pedido no encontrado.");
	        return "redirect:/inicio-cliente";
	    }

	    List<Ejemplar> ejemplares = pedido.get().getEjemplares();

	    Map<Planta, Long> plantasConCantidad = ejemplares.stream()
	            .collect(Collectors.groupingBy(Ejemplar::getPlanta, Collectors.counting()));

	    model.addAttribute("mensaje", "Factura del cliente");
	    model.addAttribute("UsuarioActual", nombreUsuario);
	    model.addAttribute("id_Cliente", id_Cliente);
	    model.addAttribute("UsuarioCliente", Usuario);
	    model.addAttribute("fechaHoy", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    model.addAttribute("direccionCliente", Usuario.getDireccionEnvio());
	    model.addAttribute("nifnieCliente", Usuario.getNif_nie());
	    model.addAttribute("emailCliente", Usuario.getEmail());
	    model.addAttribute("idPedido", idPedido);
	    model.addAttribute("plantasConCantidad", plantasConCantidad);

	    return "factura";
	}
	
	@GetMapping("/carrito-compra")
	public String carritoCompraCliente(@ModelAttribute("nombreUsuario") String nombreUsuario,
	                                   @ModelAttribute("id_Cliente") Long id_Cliente,
	                                   HttpSession session,
	                                   Model model) {
	    model.addAttribute("mensaje", "Factura del cliente");
	    model.addAttribute("UsuarioActual", nombreUsuario);
	    model.addAttribute("id_Cliente", id_Cliente);
	    
	    Pedido carrito = (Pedido) session.getAttribute("carrito");
	    model.addAttribute("carrito", carrito);
	    
	    return "carrito-compra";
	}

	
	@PostMapping("/carrito-compra")
	public String anadirAlCarrito(@RequestParam("CodigoPlanta") String codigoPlanta,
	                              @RequestParam("cantidad") int cantidad,
	                              HttpSession session,
	                              Model model) {
		
	    int disponibles = S_ejemplar.contarEjemplaresDisponibles(codigoPlanta);
	    if (cantidad > disponibles) {
	        model.addAttribute("error", "No hay suficientes ejemplares disponibles para esa planta.");
	        return "redirect:/inicio-cliente";
	    }
	    
	    List<Ejemplar> ejemplaresSeleccionados = S_ejemplar.obtenerPrimerosEjemplaresDisponibles(codigoPlanta, cantidad);
	    
	    Pedido carrito = (Pedido) session.getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new Pedido();
	        carrito.setFechaPedido(LocalDate.now());
	        carrito.setEjemplares(new ArrayList<>());
	        session.setAttribute("carrito", carrito);
	    }

	    carrito.getEjemplares().addAll(ejemplaresSeleccionados);
	    
	    session.setAttribute("carrito", carrito);
	    
	    return "redirect:/carrito-compra";
	}
	
	@PostMapping("/confirmar-pedido")
	public String confirmarPedido(HttpSession session, 
	                              @ModelAttribute("id_Cliente") Long id_Cliente,
	                              RedirectAttributes redirectAttributes) {
	    Pedido carrito = (Pedido) session.getAttribute("carrito");

	    if (carrito == null || carrito.getEjemplares().isEmpty()) {
	        redirectAttributes.addFlashAttribute("error", "El carrito está vacío.");
	        return "redirect:/carrito-compra";
	    }

	    Optional<Cliente> cliente = S_cliente.buscarPorId(id_Cliente);
	    if (!cliente.isPresent()) { 
	        redirectAttributes.addFlashAttribute("error", "Cliente no encontrado.");
	        return "redirect:/carrito-compra";
	    }

	    Cliente clienteActual = cliente.get(); 
	    carrito.setCliente(clienteActual); 
	    carrito.setEstado(EstadoPedido.REALIZADO);
	    carrito.setFechaPedido(LocalDate.now());

	    for (Ejemplar ejemplar : carrito.getEjemplares()) {
	        ejemplar.setDisponible(false);
	        S_ejemplar.modificarEjemplar(ejemplar);
	    }

	    S_pedido.guardarPedido(carrito);
	    Long idPedido = carrito.getId();

	    redirectAttributes.addAttribute("idPedido", idPedido);

	    session.removeAttribute("carrito");

	    redirectAttributes.addFlashAttribute("success", "Pedido realizado con éxito.");
	    return "redirect:/factura";
	}
	
	@GetMapping("/mispedidos")
    public String pedidosCliente(@RequestParam(value = "estado", required = false, defaultValue = "todos") String estado,
    		                     @ModelAttribute("nombreUsuario") String nombreUsuario,
                                 @ModelAttribute("UsuarioCliente") Cliente cliente,
                                 Model model) {
        if ("todos".equalsIgnoreCase(estado)) {
        	model.addAttribute("pedidos", S_pedido.findByCliente(cliente));
        } else { 
            model.addAttribute("pedidos", S_pedido.findByEstado(EstadoPedido.valueOf(estado)));
        }

        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("estados", EstadoPedido.values());
        model.addAttribute("estadoSeleccionado", estado);
        return "mispedidos";
    }

	@PostMapping("/cancelar-pedido")
	public String cancelarPedido(@RequestParam("pedidoId") Long pedidoId, Model model) {
		Optional<Pedido> pedido = S_pedido.buscarPedidoPorId(pedidoId);
		Pedido pedidoCliente = pedido.get();

		if (!pedidoCliente.getEstado().equals(EstadoPedido.ENTREGADO)
			&& !pedidoCliente.getEstado().equals(EstadoPedido.COMPLETADO)) {

			pedidoCliente.setEstado(EstadoPedido.CANCELADO);

			S_pedido.guardarPedido(pedidoCliente);
		}

		return "redirect:/mispedidos";
	}


}
