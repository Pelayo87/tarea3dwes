package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
	List<Mensaje> findByPersonaId(long personaId);
    List<Mensaje> findByEjemplarId(long ejemplarId);

}
