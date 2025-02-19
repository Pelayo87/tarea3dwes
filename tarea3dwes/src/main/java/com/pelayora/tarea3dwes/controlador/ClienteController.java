package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Cliente;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class ClienteController { 
	
	LocalDate Fechahoy = LocalDate.now();
	String fechahoyFormateada = Fechahoy.getDayOfMonth() + " de " +
			 Fechahoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + " de " +
			 Fechahoy.getYear();
	
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
}
