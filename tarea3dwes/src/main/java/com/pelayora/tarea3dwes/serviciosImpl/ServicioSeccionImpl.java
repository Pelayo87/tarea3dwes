package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.modelo.Seccion;
import com.pelayora.tarea3dwes.repositorios.SeccionRepository;
import com.pelayora.tarea3dwes.servicios.ServicioSeccion;

@Service
public class ServicioSeccionImpl implements ServicioSeccion{
	
	@Autowired
    private SeccionRepository seccion_R;
	
	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	Scanner sc = new Scanner(System.in);

	@Override
	public Seccion guardarSeccion(Seccion seccion) {
	    String nombre;
	    double area = 0;

	    // Validación del nombre
	    boolean nombreCorrecto = false;
	    do {
	        System.out.println("Dame el nombre de la sección:");
	        nombre = sc.nextLine().trim().toUpperCase();
	        if (nombre == null || nombre.trim().isEmpty()) {
	            System.err.println("El nombre de la sección no puede ser nulo o vacío.");
	        } else if (!LETTERS_ONLY_PATTERN.matcher(nombre).matches()) {
	            System.err.println("El nombre de la sección solo puede contener letras.");
	        } else {
	            nombreCorrecto = true;
	        }
	    } while (!nombreCorrecto);

	    // Validación del área
	    boolean areaCorrecta = false;
	    do {
	        try {
	            System.out.println("Dame el área de la sección (metros cuadrados(m²)):");
	            area = Double.parseDouble(sc.nextLine().trim());
	            if (area <= 0) {
	                System.err.println("El área debe ser un número positivo.");
	            } else {
	                areaCorrecta = true;
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("El área debe ser un número válido.");
	        }
	    } while (!areaCorrecta);

	    Seccion nuevaSeccion = new Seccion(nombre, area);

	    return seccion_R.save(nuevaSeccion);
	}


	@Override
	public Optional<Seccion> obtenerSeccionPorId(Long id) {
		return seccion_R.findById(id);
	}

	@Override
	public List<Seccion> obtenerSeccionPorNombre(String nombre) {
		return seccion_R.findSeccionByNombre(nombre);
	}

	@Override
	public List<Seccion> obtenerTodasLasSecciones() {
		return seccion_R.findAll();
	}

	@Override
	public void eliminarSeccion(Long id) {
		seccion_R.deleteById(id);
	}

}
