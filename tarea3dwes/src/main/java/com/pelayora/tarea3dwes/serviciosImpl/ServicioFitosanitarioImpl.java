package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Fitosanitario;
import com.pelayora.tarea3dwes.repositorios.FitosanitarioRepository;
import com.pelayora.tarea3dwes.servicios.ServicioFitosanitario;

@Service
public class ServicioFitosanitarioImpl implements ServicioFitosanitario {

    @Autowired
    private FitosanitarioRepository fitosanitario_R;

    private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private Scanner sc = new Scanner(System.in);

    public Fitosanitario guardarFitosanitario(Fitosanitario fitosanitario) {
        String nombre;
        String marca;
        boolean eco = false;

        // Validación del nombre del fitosanitario
        boolean nombreCorrecto = false;
        do {
            System.out.println("Dame el nombre del nuevo fitosanitario:");
            nombre = sc.nextLine().trim().toUpperCase();

            if (nombre.isEmpty()) {
                System.err.println("El nombre no puede ser nulo o vacío. Inténtelo de nuevo.");
            } else if (!LETTERS_ONLY_PATTERN.matcher(nombre).matches()) {
                System.err.println("El nombre solo puede contener letras. Inténtelo de nuevo.");
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);

        // Validación de la marca del fitosanitario
        boolean marcaCorrecto = false;
        do {
            System.out.println("Dame la marca del fitosanitario:");
            marca = sc.nextLine().trim().toUpperCase();

            if (marca.isEmpty()) {
                System.err.println("La marca del fitosanitario no puede ser nula o vacía.");
            } else if (!LETTERS_ONLY_PATTERN.matcher(marca).matches()) {
                System.err.println("La marca del fitosanitario solo puede contener letras.");
            } else {
                marcaCorrecto = true;
            }
        } while (!marcaCorrecto);

        // Validación del atributo eco (boolean)
        boolean ecoCorrecto = false;
        do {
            System.out.println("¿El fitosanitario es ecológico(eco)? (S/N):");
            String respuesta = sc.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                eco = true;
                ecoCorrecto = true;
            } else if (respuesta.equals("N")) {
                eco = false;
                ecoCorrecto = true;
            } else {
                System.err.println("Respuesta inválida. Debe ser 'S' o 'N'.");
            }
        } while (!ecoCorrecto);

        Fitosanitario nuevoFitosanitario = new Fitosanitario(nombre,marca,eco);
        return fitosanitario_R.saveAndFlush(nuevoFitosanitario);
    }
    
	@Override
	public Optional<Fitosanitario> obtenerFitosanitariosPorId(Long id) {
		return fitosanitario_R.findById(id);
	}

	@Override
	public List<Fitosanitario> obtenerFitosanitariosPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fitosanitario> obtenerTodosLosFitosanitarios() {
		return fitosanitario_R.findAll();
	}

	@Override
	public void eliminarFitosanitario(Long id) {
	    fitosanitario_R.deleteById(id);
	}


}
