package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Planta;

@Service
public interface ServicioCliente {
	
	List<Cliente> listarClientes();
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente guardarPlantasFavoritasCliente(Cliente cliente);
    void eliminarCliente(Long id);
    List<Planta> findPlantasByClienteId(Long id_Cliente);

}
