package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Localizacion;
import com.pelayora.tarea3dwes.modelo.Seccion;

@Repository
public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {
	
	@Query("SELECT l FROM Localizacion l LEFT JOIN Ejemplar e ON l.id_localizacion = e.localizacion.id_localizacion WHERE e.localizacion.id_localizacion IS NULL")
	List<Localizacion> findLocalizacionesSinEjemplar();
	
	List<Localizacion> findBySeccion(Seccion seccion);

}
