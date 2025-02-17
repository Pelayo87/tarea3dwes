package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Pedido;
import com.pelayora.tarea3dwes.repositorios.PedidoRepository;
import com.pelayora.tarea3dwes.servicios.ServicioPedido;

@Service
public class ServicioPedidoImpl implements ServicioPedido{
	
	@Autowired
    private PedidoRepository pedido_R;

	@Override
	public Pedido guardarPedido(Pedido pedido) {
		return pedido_R.save(pedido);
	}

	@Override
	public Pedido modificarPedido(Pedido pedido) {
		return pedido_R.save(pedido);
	}

	@Override
	public Optional<Pedido> buscarPedidoPorId(Long id) {
		return pedido_R.findById(id);
	}

	@Override
	public List<Pedido> listarPedidos() {
		return pedido_R.findAll();
	}

	@Override
	public void eliminarPedido(Pedido pedido) {
		pedido_R.delete(pedido);
		
	}

	@Override
	public boolean existPedidoPorId(Long id) {
		return pedido_R.existsById(id);
	}

}
