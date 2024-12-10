package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.repositorios.EjemplarRepository;
import com.pelayora.tarea3dwes.servicios.ServicioEjemplar;

@Service
public class ServicioEjemplarImpl implements ServicioEjemplar{
	@Autowired
	private EjemplarRepository ejemplar_R;

    @Override
    public Ejemplar guardarEjemplar(Ejemplar ejemplar) {
        return ejemplar_R.save(ejemplar);
    }

    @Override
    public Optional<Ejemplar> obtenerEjemplarPorId(Long id) {
        return ejemplar_R.findById(id);
    }
    
    public List<Ejemplar> findEjemplarByNombre(String nombre){
    	return ejemplar_R.findEjemplaresByNombre(nombre);
    }
    
    @Override
    public List<Ejemplar> obtenerTodosLosEjemplares() {
        return ejemplar_R.findAll();
    }

    @Override
    public void eliminarEjemplar(Long id) {
    	ejemplar_R.deleteById(id);
    }

    public List<Ejemplar> obtenerEjemplarPorPlanta(String codigoPlanta) {
        return ejemplar_R.findByPlanta_Codigo(codigoPlanta);
    }
}
