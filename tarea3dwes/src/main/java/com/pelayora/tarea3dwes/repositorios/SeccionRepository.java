package com.pelayora.tarea3dwes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Seccion;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Long>{
	
	@Query("SELECT s FROM Seccion s WHERE s.nombre = :nombre")
	List<Seccion> findSeccionByNombre(@Param("nombre") String nombre);

}
