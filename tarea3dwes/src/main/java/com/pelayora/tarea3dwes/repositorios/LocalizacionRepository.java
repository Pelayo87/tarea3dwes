package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Localizacion;

@Repository
public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {
	
	@Query("SELECT l FROM Localizacion l WHERE l.numseccion = :numseccion")
	List<Localizacion> findLocalizacionByNumSeccion(@Param("numSeccion") int numSeccion);

}
