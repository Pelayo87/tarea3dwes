package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Planta;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-11
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con las plantas. Proporciona métodos para guardar, 
//modificar, buscar por código, listar todas las plantas y eliminar 
//plantas del sistema.
//--------------------------------------------------------


@Service
public interface ServicioPlanta {
	
	Planta guardarPlanta(Planta planta);
	Planta modificarPlanta(String codigo);
    Optional<Planta> buscarPlantaPorId(String codigo);
    List<Planta> listarPlantas();
    void eliminarPlanta(String codigo);
}
