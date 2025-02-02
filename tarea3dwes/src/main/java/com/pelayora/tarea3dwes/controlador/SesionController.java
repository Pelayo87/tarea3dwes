package com.pelayora.tarea3dwes.controlador;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	    
	    private static final Pattern validacionFormatoNifNie = Pattern.compile("^(?:[XYZ][0-9]{7}|[0-9]{8})[A-Z]$");
	    
	    protected long id_Cliente;
	    protected long id_Persona;
	    
	    @GetMapping("/iniciosesion-registrarse")
	    public String mostrarFormulario(@RequestParam(value = "action", required = false) String action, Model model) {
	        model.addAttribute("mostrarRegistro", "registro".equals(action));
	        return "iniciosesion-registrarse";
	    }

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
				System.err.println("Usuario o contraseña incorrectos.");
	            return "iniciosesion-registrarse";
	        }
	    }
	    
	    @PostMapping("/registro")
	    public String registro(@RequestParam("nombre") String nombre,
	                           @RequestParam("nif_nie") String nif_nie,
	                           @RequestParam("usuario") String usuario,
	                           @RequestParam("contrasena") String contrasena,
	                           Model model) {

	        boolean hayErrores = false;

	        if (nombre == null || nombre.trim().isEmpty()) {
	            model.addAttribute("nombreError", "El nombre no puede estar vacío.");
	            hayErrores = true;
	        } else if (!nombre.matches("^[a-zA-Z\\s]+$")) {
	            model.addAttribute("nombreError", "El nombre solo debe contener letras y espacios.");
	            hayErrores = true;
	        }

	        if (nif_nie == null || nif_nie.trim().isEmpty()) {
	            model.addAttribute("nifNieError", "El NIF/NIE no puede estar vacío.");
	            hayErrores = true;
	        } else if (!validacionFormatoNifNie.matcher(nif_nie).matches()) {
	            model.addAttribute("nifNieError", "El NIF/NIE no tiene un formato válido (Ej: 12345678A o X1234567T).");
	            hayErrores = true;
	        }

	        if (usuario == null || usuario.trim().isEmpty()) {
	            model.addAttribute("usuarioError", "El nombre de usuario no puede estar vacío.");
	            hayErrores = true;
	        } else if (usuario.length() < 4) {
	            model.addAttribute("usuarioError", "El usuario debe tener al menos 4 caracteres.");
	            hayErrores = true;
	        } else if (!usuario.matches("^[a-zA-Z0-9]+$")) {
	            model.addAttribute("usuarioError", "El usuario solo debe contener letras y números.");
	            hayErrores = true;
	        } else if (!usuario.matches(".*[a-zA-Z].*") || !usuario.matches(".*[0-9].*")) {
	            model.addAttribute("usuarioError", "El usuario debe contener al menos una letra y un número.");
	            hayErrores = true;
	        }

	        if (contrasena == null || contrasena.trim().isEmpty()) {
	            model.addAttribute("passwordError", "La contraseña no puede estar vacía.");
	            hayErrores = true;
	        } else if (contrasena.length() < 8) {
	            model.addAttribute("passwordError", "La contraseña debe tener al menos 8 caracteres.");
	            hayErrores = true;
	        } else if (!contrasena.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            model.addAttribute("passwordError", "La contraseña debe contener al menos un carácter especial.");
	            hayErrores = true;
	        }

	        if (hayErrores) {
	            model.addAttribute("mostrarRegistro", true);
	            return "iniciosesion-registrarse";
	        }

	        Cliente nuevoCliente = new Cliente();
	        nuevoCliente.setNombre(nombre);
	        nuevoCliente.setNif_nie(nif_nie);
	        nuevoCliente.setFechaRegistro(LocalDate.now());

	        Cliente clienteGuardado = S_cliente.guardarCliente(nuevoCliente);
	        if (clienteGuardado == null || clienteGuardado.getId_cliente() <= 0) {
	            model.addAttribute("error", "Error al guardar el cliente. Inténtalo de nuevo.");
	            model.addAttribute("mostrarRegistro", true);
	            return "iniciosesion-registrarse";
	        }

	        Credenciales credenciales = new Credenciales();
	        credenciales.setUsuario(usuario);
	        credenciales.setPassword(contrasena);
	        credenciales.setCliente(clienteGuardado);

	        Credenciales credencialesGuardadas = S_credenciales.guardarCredenciales(credenciales);
	        if (credencialesGuardadas == null || credencialesGuardadas.getId() <= 0) {
	            model.addAttribute("error", "Error al guardar las credenciales. Inténtalo de nuevo.");
	            model.addAttribute("mostrarRegistro", true);
	            return "iniciosesion-registrarse";
	        }

	        model.addAttribute("nombreUsuario", usuario);
	        model.addAttribute("UsuarioCliente", credencialesGuardadas);
	        model.addAttribute("mensaje", "Registro completado con éxito.");
	        return "redirect:/inicio-cliente";
	    }
	}
