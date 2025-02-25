package com.pelayora.tarea3dwes.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayora.tarea3dwes.modelo.Credenciales;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Repositorio de Credenciales.
//--------------------------------------------------------

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long>{
	
	Optional<Credenciales> findByUsuario(String usuario);

	@Query("SELECT COUNT(c) > 0 FROM Credenciales c WHERE c.usuario = :usuario")
    boolean existeNombreUsuario(@Param("usuario") String usuario);

}
