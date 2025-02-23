package com.pelayora.tarea3dwes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;
import com.pelayora.tarea3dwes.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByEstado(EstadoPedido estado);
	List<Pedido> findByCliente(Cliente cliente); 
}
