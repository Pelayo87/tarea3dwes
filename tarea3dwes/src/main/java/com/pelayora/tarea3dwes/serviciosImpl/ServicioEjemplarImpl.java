package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Localizacion;
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
    public Ejemplar modificarEjemplar(Ejemplar ejemplar) {
        return ejemplar_R.save(ejemplar);
    }

    @Override
    public Optional<Ejemplar> obtenerEjemplarPorId(Long id) {
        return ejemplar_R.findById(id);
    }
    
    @Override
    public List<Ejemplar> obtenerEjemplarByNombre(String nombre){
    	return ejemplar_R.findEjemplaresByNombre(nombre);
    }
    
    @Override
    public List<Ejemplar> obtenerEjemplarPorLocalizacion(Localizacion localizacion) {
        return ejemplar_R.findByLocalizacion(localizacion);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Ejemplar> obtenerLocalizacionesLibres() {
        return ejemplar_R.findLocalizacionesLibres();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Ejemplar> obtenerLocalizacionesOcupadas() {
        return ejemplar_R.findLocalizacionesOcupadas();
    }
    
    @Override
    public List<Ejemplar> obtenerTodosLosEjemplares() {
        return ejemplar_R.findAll();
    }

    @Override
    public void eliminarEjemplar(Long id) {
    	ejemplar_R.deleteById(id);
    }
    
	public long contadorEjemplares() {
        return ejemplar_R.contarEjemplares();
    }

    public List<Ejemplar> obtenerEjemplarPorPlanta(String codigoPlanta) {
        return ejemplar_R.findByPlanta_Codigo(codigoPlanta);
    }
    
    public List<Ejemplar> obtenerEjemplaresPorNombresPlantas(List<String> nombresComunes) {
        return ejemplar_R.findByPlantaNombreComunIn(nombresComunes);
    }


    public int contarEjemplaresDisponibles(String codigoPlanta) {
        return ejemplar_R.contarEjemplaresDisponibles(codigoPlanta);
    }
    
    public List<Ejemplar> obtenerPrimerosEjemplaresDisponibles(String codigoPlanta, int cantidad) {
        return ejemplar_R.obtenerPrimerosEjemplaresDisponibles(codigoPlanta, cantidad);
    } 
    
    public List<Map<String, Object>> obtenerStockEjemplares() {
        List<Object[]> resultados = ejemplar_R.obtenerStockEjemplares();

        return resultados.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tipoPlanta", obj[0]);
            map.put("ejemplaresDisponibles", obj[1]);
            map.put("nombresEjemplares", obj[2]);
            return map;
        }).collect(Collectors.toList());
    }
}
