package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Pedido;

@Service
public interface ServicioPedido {
	
	Pedido guardarPedido(Pedido pedido);
	Pedido modificarPedido(Pedido pedido);
    Optional<Pedido> buscarPedidoPorId(Long id);
    List<Pedido> listarPedidos();
    void eliminarPedido(Pedido pedido);
    boolean existPedidoPorId(Long id);

}
