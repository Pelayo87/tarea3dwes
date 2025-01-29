package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;

@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente"})
public class SesionController {

	    @Autowired
	    private ServicioCredenciales S_credenciales;
	    
	    @Autowired
	    private ServicioCliente S_cliente;
	    
	    protected long id_Cliente;
	    protected long id_Persona;

	    @PostMapping("/login")
	    public String login(
	        @RequestParam("usuario") String usuario,
	        @RequestParam("contrasena") String contrasena, Model model) {

	        boolean Usuarioautenticado = S_credenciales.autenticar(new Credenciales(usuario, contrasena));

	        if (Usuarioautenticado) {
	        	Optional<Credenciales> credencialesAutenticadas = S_credenciales.buscarPorUsuario(usuario);
	        	
	        	Credenciales credenciales = credencialesAutenticadas.get();

				if (credenciales.getPersona() != null) {
					id_Persona = credenciales.getPersona().getId();
				} else {
					id_Persona = -1;
				}

				if (credenciales.getCliente() != null) {
					id_Cliente = credenciales.getCliente().getId_cliente();
				} else {
					id_Cliente = -1;
				}
	        	
	            model.addAttribute("nombreUsuario", usuario);
	            model.addAttribute("id_Persona", id_Persona);
	            model.addAttribute("id_Cliente", id_Cliente);
	            model.addAttribute("Usuario" , Usuarioautenticado);
	            if ("admin".equalsIgnoreCase(usuario) && "admin".equals(contrasena)) {
	                return "redirect:/inicio-admin";
	            } else {
	                return "redirect:/inicio-cliente";
	            }
	        } else {
	            model.addAttribute("error", "Usuario o contraseña incorrectos.");
	            return "iniciosesion-registrarse";
	        }
	    }
	    
	    @PostMapping("/registro")
	    public String registro(
	            @RequestParam("nombre") String nombre,
	            @RequestParam("nif_nie") String nif_nie,
	            @RequestParam("usuario") String usuario,
	            @RequestParam("contrasena") String contrasena,
	            Model model) {

	        try {
	            Cliente nuevoCliente = new Cliente();
	            nuevoCliente.setNombre(nombre);
	            nuevoCliente.setNif_nie(nif_nie);
	            nuevoCliente.setFechaRegistro(LocalDate.now());

	            Cliente clienteGuardado = S_cliente.guardarCliente(nuevoCliente);

	            if (clienteGuardado != null && clienteGuardado.getId_cliente() > 0) {
	                Credenciales credenciales = new Credenciales();
	                credenciales.setUsuario(usuario);
	                credenciales.setPassword(contrasena);
	                credenciales.setCliente(clienteGuardado);
	                
	                Credenciales credencialesGuardadas = S_credenciales.guardarCredenciales(credenciales);

	                if (credencialesGuardadas != null && credencialesGuardadas.getId() > 0) {
	                	model.addAttribute("nombreUsuario", usuario);
	                	model.addAttribute("UsuarioCliente" , credencialesGuardadas);
	                    model.addAttribute("mensaje", "Registro completado con éxito.");
	                    return "redirect:/inicio-cliente";
	                } else {
	                    model.addAttribute("error", "Error al guardar las credenciales. Inténtalo de nuevo.");
	                }
	            } else {
	                model.addAttribute("error", "Error al guardar el cliente. Inténtalo de nuevo.");
	            }
	        } catch (Exception e) {
	            model.addAttribute("error", "Ocurrió un error durante el registro: " + e.getMessage());
	        }

	        return "iniciosesion-registrarse";
	    }
	}
