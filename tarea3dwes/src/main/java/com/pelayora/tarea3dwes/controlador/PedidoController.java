package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pelayora.tarea3dwes.servicios.ServicioPedido;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class PedidoController {

    @Autowired
    private ServicioPedido S_pedido;

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


    @PostMapping("/modificar-estado")
    public String modificarEstado(@RequestParam("pedidoId") Long pedidoId, 
    		                      @RequestParam("nuevoEstado") EstadoPedido nuevoEstado,
    		                      Model model) {
    	S_pedido.modificarEstadoPedido(pedidoId, nuevoEstado);
        return "redirect:/gestion-pedidos";
    }
}