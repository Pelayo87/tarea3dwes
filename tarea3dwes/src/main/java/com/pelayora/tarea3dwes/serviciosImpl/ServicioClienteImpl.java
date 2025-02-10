package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Planta;
import com.pelayora.tarea3dwes.repositorios.ClienteRepository;
import com.pelayora.tarea3dwes.servicios.ServicioCliente;

import jakarta.transaction.Transactional;

@Service
public class ServicioClienteImpl implements ServicioCliente{
	
	@Autowired
	private ClienteRepository cliente_R;

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
		return cliente_R.save(cliente);
	}

	@Override
	public void eliminarCliente(Long id) {
		cliente_R.deleteById(id);

	}
	
	@Transactional
	public Cliente guardarPlantasFavoritasCliente(Cliente cliente) {
		return cliente_R.save(cliente);
	}

	@Override
	public List<Planta> findPlantasByClienteId(Long id_Cliente) {
		return cliente_R.findPlantasByClienteId(id_Cliente);
	}

}
