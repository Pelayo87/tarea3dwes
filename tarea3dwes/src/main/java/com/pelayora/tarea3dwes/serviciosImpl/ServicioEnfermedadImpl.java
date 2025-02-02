package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Enfermedad;
import com.pelayora.tarea3dwes.repositorios.EnfermedadRepository;
import com.pelayora.tarea3dwes.servicios.ServicioEnfermedad;

@Service
public class ServicioEnfermedadImpl implements ServicioEnfermedad{
	
	 @Autowired
	 private EnfermedadRepository enfermedad_R;
	 
	 private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	 private Scanner sc = new Scanner(System.in);

	@Override
	public Enfermedad guardarEnfermedad(Enfermedad enfermedad) {
		String nombre;
        String sintomas;
        boolean nociva = false;
        
        // Validación del nombre de la enfermedad
        boolean nombreCorrecto = false;
        do {
            System.out.println("Dame el nombre de la nueva enfermedad:");
            nombre = sc.nextLine().trim().toUpperCase();

            if (nombre.isEmpty()) {
                System.err.println("El nombre de la enfermedad no puede ser nulo o vacío. Inténtelo de nuevo.");
            } else if (!LETTERS_ONLY_PATTERN.matcher(nombre).matches()) {
                System.err.println("El nombre de la enfermedad solo puede contener letras. Inténtelo de nuevo.");
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);
        
        // Validación de los sintomas de la enfermedad
        boolean sintomasCorrecto = false;
        do {
            System.out.println("Dame la marca del fitosanitario:");
            sintomas = sc.nextLine().trim().toUpperCase();

            if (sintomas.isEmpty()) {
                System.err.println("Los sintomas de la enfermedad no puede ser nula o vacía.");
            } else if (!LETTERS_ONLY_PATTERN.matcher(sintomas).matches()) {
                System.err.println("Los sintomas de la enfermedad solo puede contener letras.");
            } else {
            	sintomasCorrecto = true;
            }
        } while (!sintomasCorrecto);
        
        // Validación del atributo eco (boolean)
        boolean nocivaCorrecto = false;
        do {
            System.out.println("¿La enfermedad es nociva? (S/N):");
            String respuesta = sc.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
            	nociva = true;
            	nocivaCorrecto = true;
            } else if (respuesta.equals("N")) {
            	nociva = false;
            	nocivaCorrecto = true;
            } else {
                System.err.println("Respuesta inválida. Debe ser 'S' o 'N'.");
            }
        } while (!nocivaCorrecto);
        
        Enfermedad nuevaEnfermedad = new Enfermedad(nombre,sintomas,nociva);
		return enfermedad_R.save(nuevaEnfermedad);
	}

	@Override
	public Enfermedad modificarEnfermedad(Enfermedad enfermedad) {
	    Enfermedad enfermedadExistente = enfermedad_R.findById(enfermedad.getId_enfermedad())
	                                                          .orElseThrow(() -> new RuntimeException("Enfermedad no encontrada"));
	    enfermedadExistente.setParasitos(enfermedad.getParasitos());
	    return enfermedad_R.save(enfermedadExistente);
	}


	@Override
	public Optional<Enfermedad> obtenerEnfermedadesPorId(Long id) {
		return enfermedad_R.findById(id);
	}

	@Override
	public List<Enfermedad> obtenerEnfermedadesPorNombre(String nombre) {
		return enfermedad_R.findEnfermedadByNombre(nombre);
	}

	@Override
	public List<Enfermedad> obtenerEnfermedadesPorSintomas(String sintomas) {
		return enfermedad_R.findEnfermedadBySintomas(sintomas);
	}

	@Override
	public List<Enfermedad> obtenerTodasLasEnfermedades() {
		return enfermedad_R.findAll();
	}

	@Override
	public void eliminarEnfermedad(Long id) {
		enfermedad_R.deleteById(id);
	}

}
