package com.pelayora.tarea3dwes.modelo;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensajes")
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "fechahora", nullable = false)
    private Date fechahora;
    
	@Column(name = "mensaje", nullable = false, length = 150)
    private String mensaje;
	
	@ManyToOne
	@MapsId("id_ejemplar")
	private long id_ejemplar;
	
	@ManyToOne
	@MapsId("id_persona")
	private long id_persona;
}
