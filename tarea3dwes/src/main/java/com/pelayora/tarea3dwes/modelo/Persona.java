package com.pelayora.tarea3dwes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Clase VO de personas.
//--------------------------------------------------------

@Entity
@Table(name = "personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_persona;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "email", length = 50)
    private String email;

	public Persona() {
		super();
	}

	public Persona(Long id_persona, String nombre, String email) {
		super();
		this.id_persona = id_persona;
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id_persona;
	}

	public void setId(Long id) {
		this.id_persona = id;
	}

	@Override
	public String toString() {
		return "Persona [id_persona=" + id_persona + ", nombre=" + nombre + ", email=" + email + "]";
	}
}