package com.pelayora.tarea3dwes.serviciosImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.repositorios.ClienteRepository;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;

@Service
public class ServicioClienteImpl implements ServicioCliente{
	
	@Autowired
	private ClienteRepository cliente_R;
	
	private static final Pattern validacionFormatoNifNie = Pattern.compile("^(?:[XYZ][0-9]{7}|[0-9]{8})[A-Z]$");
	private Scanner sc = new Scanner(System.in);

	@Override
	public List<Cliente> listarClientes() {
		return cliente_R.findAll();
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return cliente_R.findById(id);
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		String nombreCliente;
		String nif_nieCliente;

		// Validación del nombre del Cliente
		boolean nombreClienteCorrecto = false;
		do {
			System.out.println("Introduce tu nombre real:");
			nombreCliente = sc.nextLine().trim();

			if (nombreCliente.isEmpty()) {
				System.err.println("El nombre no puede estar vacío. Inténtelo de nuevo.");
			} else if (!nombreCliente.matches("[a-zA-Z]+")) {
				System.err.println("El nombre solo debe contener letras. Inténtelo de nuevo.");
			} else {
				nombreClienteCorrecto = true;
			}
		} while (!nombreClienteCorrecto);

		// Validación del NIF/NIE del Cliente
		boolean nif_nieCorrecto = false;
		do {
			System.out.println("Introduce tu NIF/NIE (12345678A/X1234567T):");
			nif_nieCliente = sc.nextLine().trim().toUpperCase();

			if (nif_nieCliente.isEmpty()) {
				System.err.println("El NIF/NIE no puede estar vacío. Inténtelo de nuevo.");
			} else if (!validacionFormatoNifNie.matcher(nif_nieCliente).matches()) {
		        System.err.println("El NIF/NIE es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
			/*} else if (cliente_R.existsByNifNie(nif_nieCliente)) {
				System.err.println("El NIF/NIE '" + nif_nieCliente + "' ya está en uso. Inténtelo de nuevo.");
			*/} else {
				nif_nieCorrecto = true;
			}
		} while (!nif_nieCorrecto);

		// Crear instancia de Persona
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(nombreCliente);
		nuevoCliente.setNif_nie(nif_nieCliente);
		nuevoCliente.getFechaNacimiento();
		nuevoCliente.setFechaRegistro(LocalDate.now());
		
		return cliente_R.save(nuevoCliente);
	}

	@Override
	public void eliminarCliente(Long id) {
		cliente_R.deleteById(id);

	}

}
