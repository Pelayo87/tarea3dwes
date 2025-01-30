package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Mensaje;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Repositorio de Mensajes.
//--------------------------------------------------------

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
	List<Mensaje> findByPersonaId(long personaId);
    List<Mensaje> findByEjemplarId(long ejemplarId);
    @Query("SELECT COUNT(m) FROM Mensaje m")
    long contarMensajes();

    @Query("SELECT m FROM Mensaje m WHERE m.ejemplar.nombre = :nombre")
    List<Mensaje> findByEjemplarNombre(@Param("nombre") String nombre);
    
    @Query("SELECT m FROM Mensaje m WHERE m.persona.nombre = :nombre")
    List<Mensaje> findByPersonaNombre(@Param("nombre") String nombre);

}
