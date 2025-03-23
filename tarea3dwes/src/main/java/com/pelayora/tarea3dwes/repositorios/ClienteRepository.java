package com.pelayora.tarea3dwes.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Cliente;
import com.pelayora.tarea3dwes.modelo.Planta;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByNombre(String nombre);
	
	@Query("SELECT c.plantas FROM Cliente c WHERE c.id_cliente = :idCliente")
	List<Planta> findPlantasByClienteId(@Param("idCliente") Long idCliente);
	
	@Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.email = :email")
    boolean existeClientePorEmail(@Param("email") String email);	
}
