package com.pelayora.tarea3dwes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Ejemplar;
import com.pelayora.tarea3dwes.modelo.Localizacion;

import jakarta.transaction.Transactional;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Repositorio de Ejemplares.
//--------------------------------------------------------

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

    @Query("SELECT e FROM Ejemplar e WHERE e.nombre = :nombre")
    List<Ejemplar> findEjemplaresByNombre(@Param("nombre") String nombre);

    @Transactional
    @Modifying
    @Query("UPDATE Ejemplar e SET e.nombre = :nombre WHERE e.id = :id")
    int actualizarNombreEjemplar(@Param("id") Long id, @Param("nombre") String nombre);

    List<Ejemplar> findByPlanta_Codigo(String codigoPlanta);
    
    List<Ejemplar> findByLocalizacion(Localizacion localizacion);

}
