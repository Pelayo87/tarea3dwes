package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Fitosanitario;

@Repository
public interface FitosanitarioRepository extends JpaRepository<Fitosanitario, Long>{
	
	@Query("SELECT COUNT(f) > 0 FROM Fitosanitario f WHERE f.id_fitosanitario = :id_fitosanitario")
    boolean existeId(@Param("id_fitosanitario") Long id);	
	
	@Query("SELECT COUNT(f) FROM Fitosanitario f")
    long contarFitosanitarios();
}


