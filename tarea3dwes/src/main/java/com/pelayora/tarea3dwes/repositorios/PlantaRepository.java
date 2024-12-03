package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, String> {
	
	default boolean existeCodigo(Planta p) {
		List<Planta> listaPlantas= findAll();
		for(Planta aux : listaPlantas) {
			if(p.getCodigo().equals(aux.getCodigo())) {
				return true;
			}
		}
		return false;
	}
	
}
