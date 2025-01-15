package com.pelayora.tarea3dwes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Parasitos;

@Repository
public interface ParasitosRepository  extends JpaRepository<Parasitos, Long>{
	
	@Query("SELECT p FROM Parasitos p WHERE p.nombre = :nombre")
    List<Parasitos> findParasitosByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT p FROM Parasitos p WHERE p.color = :color")
    List<Parasitos> findParasitosByColor(@Param("color") String color);
	
	@Query("SELECT COUNT(p) > 0 FROM Parasitos p WHERE p.id_parasito = :id_parasito")
    boolean existeId(@Param("id_parasito") Long id);	

}
