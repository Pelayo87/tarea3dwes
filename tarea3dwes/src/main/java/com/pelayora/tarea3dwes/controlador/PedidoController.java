package com.pelayora.tarea3dwes.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioPedido;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;
import com.pelayora.tarea3dwes.modelo.Pedido;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class PedidoController {

    @Autowired
    private ServicioPedido S_pedido;
    
    @Autowired
    private ServicioEjemplar S_ejemplar;

    @GetMapping("/gestion-pedidos")
    public String gestionPedidos(@RequestParam(value = "estado", required = false, defaultValue = "todos") String estado,
                                 @ModelAttribute("nombreUsuario") String nombreUsuario,
                                 Model model) {
        if ("todos".equalsIgnoreCase(estado)) {
            model.addAttribute("pedidos", S_pedido.listarPedidos());
        } else {
            model.addAttribute("pedidos", S_pedido.findByEstado(EstadoPedido.valueOf(estado)));
        }
        model.addAttribute("UsuarioActual", nombreUsuario);
        model.addAttribute("estados", EstadoPedido.values());
        model.addAttribute("estadoSeleccionado", estado);
        return "gestion-pedidos";
    }


    /**
     * Modifica el estado de un pedido según la solicitud del usuario.
     *   
     * Si el nuevo estado no es "CANCELADO", simplemente actualiza el estado del pedido.  
     * Si el nuevo estado es "CANCELADO", además de actualizar el estado,  
     * se liberan los ejemplares asociados al pedido, marcándolos como disponibles  
     * y desvinculándolos del pedido (estableciendo su atributo pedido en null).  
     * Finalmente, el pedido se guarda con el nuevo estado.  
     *
     * @param pedidoId    ID del pedido que se quiere modificar.
     * @param nuevoEstado Nuevo estado que se asignará al pedido.
     * @param model       Modelo de la vista para la gestión de pedidos.
     * @return Redirecciona a la página de gestión de pedidos después de la modificación.
     */
    @PostMapping("/modificar-estado")
    public String modificarEstado(@RequestParam("pedidoId") Long pedidoId, 
                                  @RequestParam("nuevoEstado") EstadoPedido nuevoEstado,
                                  Model model) {
        
        Optional<Pedido> pedido = S_pedido.buscarPedidoPorId(pedidoId);
        
        if (pedido.isPresent()) {
            Pedido pedidoCliente = pedido.get();

            if (nuevoEstado != EstadoPedido.CANCELADO) {
                S_pedido.modificarEstadoPedido(pedidoId, nuevoEstado);
            } else {
                List<Ejemplar> ejemplares = pedidoCliente.getEjemplares();
                
                for (Ejemplar ejemplar : ejemplares) {
                    ejemplar.setDisponible(true);
                    ejemplar.setPedido(null);
                    S_ejemplar.modificarEjemplar(ejemplar);
                }
                
                pedidoCliente.setEstado(EstadoPedido.CANCELADO);
                S_pedido.guardarPedido(pedidoCliente);
            }
        }

        return "redirect:/gestion-pedidos";
    }
}