package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.EstadoPedido;
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
	
	public List<Pedido> findByEstado(EstadoPedido estado) {
	    return pedido_R.findByEstado(estado);
	}

	@Override
	public void eliminarPedido(Pedido pedido) {
		pedido_R.delete(pedido);
		
	}

	@Override
	public boolean existPedidoPorId(Long id) {
		return pedido_R.existsById(id);
	}
	
	public void modificarEstadoPedido(Long id, EstadoPedido nuevoEstado) {
	    Pedido pedido = pedido_R.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
	    pedido.setEstado(nuevoEstado);
	    pedido_R.save(pedido);
	}

	@Override
	public List<Pedido> findByCliente(Cliente cliente) {
		return pedido_R.findByCliente(cliente);
	}
}
