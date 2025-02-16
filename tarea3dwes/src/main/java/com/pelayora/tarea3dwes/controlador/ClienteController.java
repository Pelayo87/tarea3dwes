package com.pelayora.tarea3dwes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente"})
public class ClienteController {
	
	@Autowired
    private ServicioPlanta S_planta;
    
    @Autowired
    private ServicioEjemplar S_ejemplar;
    
    @Autowired
    private ServicioFitosanitario S_fitosanitario;
    
    @Autowired
    private ServicioMensaje S_mensaje;
	
	@Autowired
	private ServicioCliente S_cliente;
	
	@GetMapping("/factura")
    public String inicioViveroCliente(@ModelAttribute("nombreUsuario") String nombreUsuario,
                                      @ModelAttribute("id_Cliente") Long id_Cliente,
                                      Model model) {
		model.addAttribute("mensaje", "Factura del cliente");
		model.addAttribute("UsuarioActual", nombreUsuario);
		model.addAttribute("id_Cliente", id_Cliente);
		return "factura";
	}
}
