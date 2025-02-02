package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Persona;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Repositorio de Personas.
//--------------------------------------------------------

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	@Query("SELECT COUNT(p) > 0 FROM Persona p WHERE p.email = :email")
    boolean existePersonaPorEmail(@Param("email") String email);	
	
	boolean existsByNombre(String nombre);
}
