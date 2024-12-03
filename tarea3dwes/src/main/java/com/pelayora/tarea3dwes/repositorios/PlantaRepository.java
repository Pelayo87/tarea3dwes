package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, String> {
	
	@Query("SELECT COUNT(p) > 0 FROM Planta p WHERE p.codigo = :codigo")
    boolean existeCodigo(@Param("codigo") String codigo);	
}
