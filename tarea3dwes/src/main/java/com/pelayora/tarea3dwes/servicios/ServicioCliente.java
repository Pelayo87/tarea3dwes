package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Cliente;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con los Clientes del vivero. Proporciono métodos 
//para listar, buscar, guardar, guardar sus plantas favoritas y eliminar.
//--------------------------------------------------------
@Service
public interface ServicioCliente {
	
	List<Cliente> listarClientes();
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    
    /**
     * Guarda las plantas favoritas de un cliente.
     *
     * @param cliente Cliente con las plantas favoritas actualizadas.
     * @return Cliente actualizado.
     */
    Cliente guardarPlantasFavoritasCliente(Cliente cliente);
    void eliminarCliente(Long id);

}
