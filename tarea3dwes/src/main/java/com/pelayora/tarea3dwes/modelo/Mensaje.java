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

@Entity
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

	public Mensaje() {
		super();
	}

	public Mensaje(long id, Date fechahora, String mensaje, long id_ejemplar, long id_persona) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.id_ejemplar = id_ejemplar;
		this.id_persona = id_persona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public long getId_ejemplar() {
		return id_ejemplar;
	}

	public void setId_ejemplar(long id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}
	
	
}
