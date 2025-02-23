package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;
import com.pelayora.tarea3dwes.modelo.Pedido;

@Service
public interface ServicioPedido {
	
	Pedido guardarPedido(Pedido pedido);
	Pedido modificarPedido(Pedido pedido);
    Optional<Pedido> buscarPedidoPorId(Long id);
    List<Pedido> listarPedidos();
    List<Pedido> findByEstado(EstadoPedido estado);
    void eliminarPedido(Pedido pedido);
    boolean existPedidoPorId(Long id);
    void modificarEstadoPedido(Long id, EstadoPedido nuevoEstado);
    List<Pedido> findByCliente(Cliente cliente);

}
