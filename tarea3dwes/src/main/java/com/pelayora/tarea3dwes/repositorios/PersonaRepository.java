package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Persona;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Repositorio de Personas.
//--------------------------------------------------------

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}
