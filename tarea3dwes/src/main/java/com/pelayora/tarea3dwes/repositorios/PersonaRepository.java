package com.pelayora.tarea3dwes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pelayora.tarea3dwes.modelo.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean ExistePersonaNombre(String nombre);
    boolean ExistePersonaCorreo(String email);
}

