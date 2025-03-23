package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Localizacion;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con los ejemplares de plantas. Proporciona métodos 
//para guardar, buscar por ID o planta, listar todos los ejemplares 
//y eliminar ejemplares del sistema.
//--------------------------------------------------------

@Service
public interface ServicioEjemplar {
    Ejemplar guardarEjemplar(Ejemplar ejemplar);
    Ejemplar modificarEjemplar(Ejemplar ejemplar);
    Optional<Ejemplar> obtenerEjemplarPorId(Long id);
    List<Ejemplar> obtenerEjemplarPorPlanta(String codigo);
    List<Ejemplar> obtenerEjemplarByNombre(String nombre);
    List<Ejemplar> obtenerEjemplarPorLocalizacion(Localizacion localizacion);
    List<Ejemplar> obtenerLocalizacionesLibres();
    List<Ejemplar> obtenerLocalizacionesOcupadas();
    List<Ejemplar> obtenerTodosLosEjemplares();
    void eliminarEjemplar(Long id);
    long contadorEjemplares();
    List<Ejemplar> obtenerEjemplaresPorNombresPlantas(List<String> nombresComunes);
    int contarEjemplaresDisponibles(String codigoPlanta);
    List<Ejemplar> obtenerEjemplaresPorPedido(Long idPedido);
    List<Ejemplar> obtenerPrimerosEjemplaresDisponibles(String codigoPlanta, int cantidad);
    List<Map<String, Object>> obtenerStockEjemplares();
    
}
