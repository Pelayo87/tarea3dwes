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
	
	@Query("SELECT e FROM Ejemplar e WHERE e.localizacion IS NULL")
    List<Ejemplar> findLocalizacionesLibres();
	
	@Query("SELECT e FROM Ejemplar e WHERE e.localizacion IS NOT NULL")
    List<Ejemplar> findLocalizacionesOcupadas();

    @Query("SELECT e FROM Ejemplar e WHERE e.nombre = :nombre")
    List<Ejemplar> findEjemplaresByNombre(@Param("nombre") String nombre);

    @Transactional
    @Modifying
    @Query("UPDATE Ejemplar e SET e.nombre = :nombre WHERE e.id = :id")
    int actualizarNombreEjemplar(@Param("id") Long id, @Param("nombre") String nombre);

    List<Ejemplar> findByPlanta_Codigo(String codigoPlanta);
    
    List<Ejemplar> findByLocalizacion(Localizacion localizacion);
    
    @Query("SELECT COUNT(e) FROM Ejemplar e")
    long contarEjemplares();

    List<Ejemplar> findByPlantaNombreComunIn(List<String> nombresComunes);
    
    @Query("SELECT COUNT(e) FROM Ejemplar e WHERE e.planta.codigo = :codigoPlanta AND e.disponible = true")
    int contarEjemplaresDisponibles(@Param("codigoPlanta") String codigoPlanta);
    
	@Query(value = "SELECT e.* " 
	        + "FROM ejemplares e " 
		    + "JOIN plantas p ON e.codigo = p.codigo "
		    + "WHERE p.codigo = :codigoPlanta " 
		    + "  AND e.disponible = true " 
		    + "ORDER BY e.id_ejemplar ASC "
		    + "LIMIT :cantidad", nativeQuery = true)
	List<Ejemplar> obtenerPrimerosEjemplaresDisponibles(@Param("codigoPlanta") String codigoPlanta,
			                                            @Param("cantidad") int cantidad);
	
	@Query(value = """
		    SELECT p.nombrecomun AS tipoPlanta, 
		           COUNT(CASE WHEN e.disponible = 1 THEN 1 END) AS ejemplaresDisponibles, 
		           GROUP_CONCAT(CONCAT(e.nombrecomun, ' = ', 
		           CASE WHEN e.disponible = 1 THEN 'Disponible' ELSE 'No Disponible' END) SEPARATOR ', ') 
		    FROM ejemplares e 
		    JOIN plantas p ON e.codigo = p.codigo 
		    GROUP BY p.nombrecomun
		    """, nativeQuery = true)
		List<Object[]> obtenerStockEjemplares();

	
	@Query("SELECT e FROM Ejemplar e WHERE e.pedido.id = :idPedido")
	List<Ejemplar> obtenerEjemplaresPorPedido(@Param("idPedido") Long idPedido);

}
