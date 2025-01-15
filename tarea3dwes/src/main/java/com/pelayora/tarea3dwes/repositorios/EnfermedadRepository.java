package com.pelayora.tarea3dwes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayora.tarea3dwes.modelo.Enfermedad;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long>{
	
	@Query("SELECT e FROM Enfermedad e WHERE e.nombre = :nombre")
    List<Enfermedad> findEnfermedadByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT e FROM Enfermedad e WHERE e.sintomas = :sintomas")
    List<Enfermedad> findEnfermedadBySintomas(@Param("sintomas") String sintomas);
}
