package com.pelayora.tarea3dwes.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayora.tarea3dwes.modelo.Credenciales;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long>{
	
	Optional<Credenciales> findByUsuario(String usuario);

}
