package com.pelayora.tarea3dwes.modelo;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@JoinColumn(name = "id_ejemplar")
	private Ejemplar ejemplar;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona persona;

	public Mensaje() {
		super();
	}

	public Mensaje(long id, Date fechahora, String mensaje, Ejemplar ejemplar, Persona persona) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.ejemplar = ejemplar;
		this.persona = persona;
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

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}