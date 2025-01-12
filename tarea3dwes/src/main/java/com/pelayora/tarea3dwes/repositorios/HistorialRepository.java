package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long>{

}
