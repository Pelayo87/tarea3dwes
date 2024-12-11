package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;
import com.pelayora.tarea3dwes.repositorios.PersonaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;

import jakarta.annotation.PostConstruct;

@Service
public class ServicioCredencialesImpl implements ServicioCredenciales {

	@Autowired
    private CredencialesRepository credencialesRepository;

    @Autowired
    private PersonaRepository personaRepository;
	
	private Scanner sc = new Scanner(System.in);

    @Override
    public List<Credenciales> listarCredenciales() {
        return credencialesRepository.findAll();
    }

    @Override
    public Optional<Credenciales> buscarPorId(long id) {
        return credencialesRepository.findById(id);
    }

    @Override
    public Optional<Credenciales> buscarPorUsuario(String usuario) {
        return credencialesRepository.findByUsuario(usuario);
    }
    
    @PostConstruct
    public void crearAdminSiNoExiste() {
        // Verificar si ya existe el administrador
        Optional<Credenciales> admin = credencialesRepository.findByUsuario("admin");
        if (admin.isEmpty()) {
            // Crear un nuevo usuario administrador
            Persona personaAdmin = new Persona();
            personaAdmin.setNombre("Admin");
            personaAdmin.setEmail("admin@admin.com");
            personaAdmin = personaRepository.save(personaAdmin);

            Credenciales credencialesAdmin = new Credenciales();
            credencialesAdmin.setUsuario("admin");
            credencialesAdmin.setPassword("admin");
            credencialesAdmin.setPersona(personaAdmin);

            credencialesRepository.save(credencialesAdmin);
            System.out.println("Administrador creado con éxito.");
        } else {
            System.out.println("El usuario administrador ya existe.");
        }
    }

    @Override
    public Credenciales guardarCredenciales(Credenciales credenciales) {
    	String nombreUsuario;
	    String password;

	    // Validación nombre de usuario
	    boolean nombreCorrecto = false;
	    do {
	        System.out.println("Introduce un nombre de usuario:");
	        nombreUsuario = sc.nextLine().trim();

	        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
	            System.err.println("El nombre de usuario no puede ser nulo o vacío. Inténtelo de nuevo.");
	        } else if (nombreUsuario.length() < 4) {
	            System.err.println("El nombre de usuario debe tener al menos 4 caracteres. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches("[a-zA-Z0-9]+")) {
	            System.err.println("El nombre de usuario solo debe contener letras y números, sin espacios. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches(".*[a-zA-Z].*")) {
	            System.err.println("El nombre de usuario debe contener al menos una letra. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches(".*[0-9].*")) {
	            System.err.println("El nombre de usuario debe contener al menos un número. Inténtelo de nuevo.");
	        } else {
	            nombreCorrecto = true;
	        }

	    } while (!nombreCorrecto);

	    // Validación contraseña
	    boolean passwordCorrecto = false;
	    do {
	        System.out.println("Introduce una contraseña:");
	        password = sc.nextLine().trim();

	        if (password == null || password.isEmpty()) {
	            System.err.println("La contraseña no puede ser nula o vacía. Inténtelo de nuevo.");
	        } else if (password.length() < 8) {
	            System.err.println("La contraseña debe tener al menos 8 caracteres. Inténtelo de nuevo.");
	        } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            System.err.println("La contraseña debe contener al menos un carácter especial. Inténtelo de nuevo.");
	        } else {
	            passwordCorrecto = true;
	        }

	    } while (!passwordCorrecto);
        return credencialesRepository.save(credenciales);
    }

    @Override
    public void eliminarCredenciales(long id) {
        credencialesRepository.deleteById(id);
    }
    
    @Override
    public boolean autenticar(Credenciales credenciales) {
        Optional<Credenciales> credencialesDB = buscarPorUsuario(credenciales.getUsuario());
        return credencialesDB.isPresent() && 
               credencialesDB.get().getPassword().equals(credenciales.getPassword());
    }

}
