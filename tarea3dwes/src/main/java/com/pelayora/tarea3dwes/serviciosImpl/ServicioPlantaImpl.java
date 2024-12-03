package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.PlantaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPlanta;

@Service
public class ServicioPlantaImpl implements ServicioPlanta {
	@Autowired
	private PlantaRepository planta_R;

	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	Scanner sc = new Scanner(System.in);

	@Override
	public Planta guardarPlanta(Planta planta) {
		String codigo;
		String nombrecomun;
		String nombrecientifico;

		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la nueva planta:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
//			} else if (pdi.ExistePlanta(codigo)) {
//				System.err.println("El código '" + codigo + "' ya está en uso. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);

		// Validación del nombre común
		boolean nombreComunCorrecto = false;
		do {
			System.out.println("Dame el nombre común de la planta:");
			nombrecomun = sc.nextLine().trim().toUpperCase();
			if (nombrecomun == null || nombrecomun.trim().isEmpty()) {
				System.err.println("El nombre común de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecomun).matches()) {
				System.err.println("El nombre común de la planta solo puede contener letras.");
			} else {
				nombreComunCorrecto = true;
			}
		} while (!nombreComunCorrecto);

		// Validación del nombre científico
		boolean nombreCientificoCorrecto = false;
		do {
			System.out.println("Dame el nombre científico de la planta:");
			nombrecientifico = sc.nextLine().trim().toUpperCase();
			if (nombrecientifico == null || nombrecientifico.trim().isEmpty()) {
				System.err.println("El nombre científico de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecientifico).matches()) {
				System.err.println("El nombre científico de la planta solo puede contener letras.");
			} else {
				nombreCientificoCorrecto = true;
			}
		} while (!nombreCientificoCorrecto);

		Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);
		return planta_R.saveAndFlush(nuevaplanta);
	}

	@Override
	public Planta modificarPlanta(String codigo) {
		String nombrecomun;
		String nombrecientifico;

		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la planta a modificar:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
			} else if (!planta_R.existsById(codigo)) {
				System.err.println("El código '" + codigo + "' no existe. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);

		Optional<Planta> plantaOptional = planta_R.findById(codigo);
		if (plantaOptional.isPresent()) {
			Planta plantaExistente = plantaOptional.get();

			// Validación del nombre común
			boolean nombreComunCorrecto = false;
			do {
				System.out.println(
						"Dame el nuevo nombre común de la planta (actual: " + plantaExistente.getNombreComun() + "):");
				nombrecomun = sc.nextLine().trim().toUpperCase();
				if (nombrecomun == null || nombrecomun.trim().isEmpty()) {
					System.err.println("El nombre común no puede ser nulo o vacío.");
				} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecomun).matches()) {
					System.err.println("El nombre común solo puede contener letras.");
				} else {
					nombreComunCorrecto = true;
				}
			} while (!nombreComunCorrecto);
			plantaExistente.setNombreComun(nombrecomun);

			// Validación del nombre científico
			boolean nombreCientificoCorrecto = false;
			do {
				System.out.println("Dame el nuevo nombre científico de la planta (actual: "
						+ plantaExistente.getNombreCientifico() + "):");
				nombrecientifico = sc.nextLine().trim().toUpperCase();
				if (nombrecientifico == null || nombrecientifico.trim().isEmpty()) {
					System.err.println("El nombre científico no puede ser nulo o vacío.");
				} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecientifico).matches()) {
					System.err.println("El nombre científico solo puede contener letras.");
				} else {
					nombreCientificoCorrecto = true;
				}
			} while (!nombreCientificoCorrecto);
			plantaExistente.setNombreCientifico(nombrecientifico);

			// Guardar los cambios
			planta_R.save(plantaExistente);
			System.out.println("Planta modificada correctamente.");
			return plantaExistente;
		} else {
			System.err.println("No se encontró la planta con el código especificado.");
			return null;
		}
	}

	@Override
	public Optional<Planta> buscarPlantaPorId(String codigo) {
		return planta_R.findById(codigo);
	}

//	@Override
	public List<Planta> listarPlantas() {
		return planta_R.findAll();
	}

	@Override
	public void eliminarPlanta(String codigo) {
		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la planta a eliminar:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);

		try {
			if (planta_R.existsById(codigo)) {
				planta_R.deleteById(codigo);
				System.out.println("Planta eliminada con éxito.");
			} else {
				System.err.println("No existe una planta con el código proporcionado.");
			}
		} catch (Exception e) {
			System.err.println("Error al eliminar la planta: " + e.getMessage());
		}
	}
}
