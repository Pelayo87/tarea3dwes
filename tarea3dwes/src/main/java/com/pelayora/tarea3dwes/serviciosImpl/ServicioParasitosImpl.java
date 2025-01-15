package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Fitosanitario;
import com.pelayora.tarea3dwes.modelo.Parasitos;
import com.pelayora.tarea3dwes.repositorios.ParasitosRepository;
import com.pelayora.tarea3dwes.servicios.ServicioParasitos;

@Service
public class ServicioParasitosImpl implements ServicioParasitos{
	
	@Autowired
    private ParasitosRepository parasitos_R;
	
	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	private Scanner sc = new Scanner(System.in);

	@Override
	public Parasitos guardarParasitos(Parasitos parasitos) {
		String nombre;
		String color;

		// Validación del nombre del parasito
		boolean nombreCorrecto = false;
		do {
			System.out.println("Dame el nombre del nuevo parasito:");
			nombre = sc.nextLine().trim().toUpperCase();

			if (nombre.isEmpty()) {
				System.err.println("El nombre del parasito no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombre).matches()) {
				System.err.println("El nombre del parasito solo puede contener letras. Inténtelo de nuevo.");
			} else {
				nombreCorrecto = true;
			}
		} while (!nombreCorrecto);

		// Validación de la marca del fitosanitario
		boolean colorCorrecto = false;
		do {
			System.out.println("Dame la marca del fitosanitario:");
			color = sc.nextLine().trim().toUpperCase();

			if (color.isEmpty()) {
				System.err.println("El color del parasito no puede ser nula o vacía.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(color).matches()) {
				System.err.println("El color del parasito solo puede contener letras.");
			} else {
				colorCorrecto = true;
			}
		} while (!colorCorrecto);
		
		Parasitos nuevoParasito = new Parasitos(nombre,color);
		return parasitos_R.save(nuevoParasito);
	}

	@Override
	public Parasitos modificarParasitos(Long id) {
		String nombre;
		String color;

		// Validación del id del parasito
		boolean IdCorrecto = false;

		do {
			System.out.println("Dame el id del fitosanitario a modificar:");
			if (sc.hasNextLong()) {
				id = sc.nextLong();
				if (!parasitos_R.existeId(id)) {
					System.err.println("El id '" + id + "' no existe. Inténtelo de nuevo.");
				} else {
					IdCorrecto = true;
				}
			} else {
				System.err.println("El id debe ser un número válido.");
				sc.next();
			}
		} while (!IdCorrecto);

		Optional<Parasitos> parasitos = parasitos_R.findById(id);
		if (parasitos.isPresent()) {
			Parasitos parasitoExistente = parasitos.get();

			// Validación del nombre
			boolean nombreCorrecto = false;
			do {
				System.out
						.println("Dame el nuevo nombre del parasitos (actual: " + parasitoExistente.getNombre() + "):");
				nombre = sc.nextLine().trim().toUpperCase();
				if (nombre == null || nombre.trim().isEmpty()) {
					System.err.println("El nombre no puede ser nulo o vacío.");
				} else if (!LETTERS_ONLY_PATTERN.matcher(nombre).matches()) {
					System.err.println("El nombre solo puede contener letras.");
				} else {
					nombreCorrecto = true;
				}
			} while (!nombreCorrecto);
			parasitoExistente.setNombre(nombre);

			// Validación del color
			boolean marcaCorrecta = false;
			do {
				System.out.println("Dame el nuevo color del parasito (actual: " + parasitoExistente.getColor() + "):");
				color = sc.nextLine().trim().toUpperCase();
				if (color == null || color.trim().isEmpty()) {
					System.err.println("El color no puede ser nula o vacía.");
				} else if (!LETTERS_ONLY_PATTERN.matcher(color).matches()) {
					System.err.println("El color solo puede contener letras.");
				} else {
					marcaCorrecta = true;
				}
			} while (!marcaCorrecta);
			parasitoExistente.setColor(color);

			// Guardo los cambios
			parasitos_R.save(parasitoExistente);
			System.out.println("Parasito modificado correctamente.");
			return parasitoExistente;
		} else {
			System.err.println("No se encontró el parasito con el id proporcionado.");
			return null;
		}
	}

	@Override
	public Optional<Parasitos> obtenerParasitosPorId(Long id) {
		return parasitos_R.findById(id);
	}

	@Override
	public List<Parasitos> obtenerParasitosPorNombre(String nombre) {
		return parasitos_R.findParasitosByNombre(nombre);
	}

	@Override
	public List<Parasitos> obtenerParasitosPorColor(String color) {
		return parasitos_R.findParasitosByColor(color);
	}

	@Override
	public List<Parasitos> obtenerTodosLosParasitos() {
		return parasitos_R.findAll();
	}

	@Override
	public void eliminarEnfermedad(Long id) {
		parasitos_R.deleteById(id);
		
	}
	

}
