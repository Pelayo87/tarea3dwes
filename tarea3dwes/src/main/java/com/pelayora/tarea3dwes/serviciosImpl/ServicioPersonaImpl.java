package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.repositorios.PersonaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPersona;

@Service
public class ServicioPersonaImpl implements ServicioPersona {
	@Autowired
	private PersonaRepository persona_R;
	
private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$");
    
    private Scanner sc = new Scanner(System.in);

	@Override
	public List<Persona> listarPersonas() {
		return persona_R.findAll();
	}

	@Override
	public Optional<Persona> buscarPorId(Long id) {
		return persona_R.findById(id);
	}

	@Override
	public Persona guardarPersona(Persona persona) {
	    String nombrePersona;
	    String emailPersona;

	    // Validación nombre real
	    boolean nombreCorrecto = false;
	    do {
	        System.out.println("Introduce tu nombre real:");
	        nombrePersona = sc.nextLine().trim();

	        if (nombrePersona.isEmpty()) {
	            System.err.println("El nombre no puede estar vacío. Inténtelo de nuevo.");
	        } else if (!nombrePersona.matches("[a-zA-Z]+")) {
	            System.err.println("El nombre solo debe contener letras. Inténtelo de nuevo.");
	        } else if (persona_R.ExistePersonaNombre(nombrePersona)) {
	            System.err.println("Ya hay un '" + nombrePersona + "' en uso. Inténtelo de nuevo.");
	        } else {
	            nombreCorrecto = true;
	        }
	    } while (!nombreCorrecto);

	    // Validación email
	    boolean emailCorrecto = false;
	    do {
	        System.out.println("Introduce tu email (correo electrónico):");
	        emailPersona = sc.nextLine().trim().toLowerCase();

	        if (emailPersona.isEmpty()) {
	            System.err.println("El email no puede estar vacío. Inténtelo de nuevo.");
	        } else if (!EMAIL_PATTERN.matcher(emailPersona).matches()) {
	            System.err.println("El email es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
	        } else if (persona_R.ExistePersonaCorreo(emailPersona)) {
	            System.err.println("El correo '" + emailPersona + "' ya está en uso. Inténtelo de nuevo.");
	        } else {
	            emailCorrecto = true;
	        }
	    } while (!emailCorrecto);

	    // Crear instancia de Persona
	    Persona nuevaPersona = new Persona();
	    nuevaPersona.setNombre(nombrePersona);
	    nuevaPersona.setEmail(emailPersona);
	    return persona_R.save(nuevaPersona);
	}


	@Override
	public void eliminarPersona(Long id) {
		persona_R.deleteById(id);
	}
}
