package com.pelayora.tarea3dwes.controlador;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;


/**
 * Controlador encargado de gestionar la sesión de los usuarios.
 * Permite iniciar sesión, registrarse y gestionar credenciales de usuario.
 */
@Controller
@SessionAttributes({"nombreUsuario", "id_Persona", "id_Cliente", "UsuarioCliente", "UsuarioPersona"})
public class SesionController {

	    @Autowired
	    private ServicioCredenciales S_credenciales;
	    
	    @Autowired
	    private ServicioCliente S_cliente;
	    
	    private static final Pattern validacionFormatoNifNie = Pattern.compile("^(?:[XYZ][0-9]{7}|[0-9]{8})[A-Z]$");
	    
	    protected long id_Cliente;
	    protected long id_Persona;
	    	    
	    /**
	     * Muestra el formulario de inicio de sesión y registro.
	     * 
	     * @param action Acción opcional para mostrar el formulario de registro.
	     * @param model Modelo para la vista.
	     * @return Vista "iniciosesion-registrarse".
	     */
	    
	    @GetMapping("/login")
        public String mostrarLogin() {
	        return "iniciosesion-registrarse"; 
	    }
	    
	    @GetMapping("/registro")
	    public String mostrarRegistro(@ModelAttribute("cliente") Cliente cliente,
                                      @ModelAttribute("credenciales") Credenciales credenciales,Model model) {
	        model.addAttribute("mostrarRegistro", true);
	        model.addAttribute("cliente", new Cliente());
	        model.addAttribute("credenciales", new Credenciales());
	        return "iniciosesion-registrarse";
	    }
	    
	    /**
	     * Maneja el registro de nuevos usuarios.
	     * 
	     * @param nombre Nombre del usuario.
	     * @param nif_nie Documento identificativo.
	     * @param usuario Nombre de usuario.
	     * @param contrasena Contraseña del usuario.
	     * @param model Modelo para la vista.
	     * @return Redirección a la página de inicio del cliente o vista de registro en caso de error.
	     */
	    @PostMapping("/registro")
	    public String registro(@RequestParam("nombre") String nombre,
	                           @RequestParam("nif_nie") String nif_nie,
	                           @RequestParam("fechaNacimiento") String fechaNacimiento,
	                           @RequestParam("direccionEnvio") String direccionEnvio,
	                           @RequestParam("telefono") String telefono,
	                           @RequestParam("email") String email,
	                           @RequestParam("usuario") String usuario,
	                           @RequestParam("contrasena") String contrasena,
	                           @ModelAttribute("cliente") Cliente cliente,
                               @ModelAttribute("credenciales") Credenciales credenciales,
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
	        }

	        if (!telefono.matches("^[0-9]{9,15}$")) {
	            model.addAttribute("telefonoError", "El teléfono debe tener entre 9 y 15 dígitos numéricos.");
	            hayErrores = true;
	        }

	        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	            model.addAttribute("emailError", "El email no tiene un formato válido.");
	            hayErrores = true;
	        }

	        if (usuario.length() < 4 || !usuario.matches("^[a-zA-Z0-9]+$")) {
	            model.addAttribute("usuarioError", "El usuario debe tener al menos 4 caracteres, letras y números.");
	            hayErrores = true;
	        }

	        if (contrasena.length() < 8 || !contrasena.matches(".*[!@#$%^&*()].*")) {
	            model.addAttribute("passwordError", "La contraseña debe tener al menos 8 caracteres y un símbolo.");
	            hayErrores = true;
	        }

	        if (hayErrores) {
	            model.addAttribute("mostrarRegistro", true);
	            return "iniciosesion-registrarse";
	        }

	        Cliente clienteGuardado = S_cliente.guardarCliente(cliente);
	        if (clienteGuardado == null || clienteGuardado.getId_cliente() <= 0) {
	            model.addAttribute("error", "Error al registrar el cliente.");
	            return "iniciosesion-registrarse";
	        }

	        credenciales.setUsuario(usuario);
	        credenciales.setPassword(contrasena);
	        credenciales.setCliente(clienteGuardado);        
	        Credenciales credencialesGuardadas = S_credenciales.guardarCredenciales(credenciales);
	        if (credencialesGuardadas == null || credencialesGuardadas.getId() <= 0) {
	            model.addAttribute("error", "Error al registrar las credenciales.");
	            return "iniciosesion-registrarse";
	        }

	        return "redirect:/login";
	    }

	}
