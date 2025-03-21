package com.pelayora.tarea3dwes.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Credenciales;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-02-13
//Descripción: Interfaz de servicio para gestionar las operaciones
//relacionadas con las credenciales de usuario. Proporciono métodos 
//para listar, buscar, guardar, eliminar y autenticar credenciales.
//--------------------------------------------------------

@Service
public interface ServicioCredenciales {
	
	List<Credenciales> listarCredenciales();
    Optional<Credenciales> buscarPorId(long id);
    Optional<Credenciales> buscarPorUsuario(String usuario);
    boolean existeNombreUsuario(String usuario);
    Credenciales guardarCredenciales(Credenciales credenciales);
    void eliminarCredenciales(long id);
    boolean autenticar(Credenciales credenciales);
}
