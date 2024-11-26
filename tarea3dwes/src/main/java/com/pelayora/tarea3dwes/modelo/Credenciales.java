package com.pelayora.tarea3dwes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "credenciales")
public class Credenciales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "usuario", nullable = false, length = 50)
	private String usuario;
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@OneToOne
	@MapsId("id_persona")
	private long id_persona;

	public Credenciales() {
		super();
	}

	public Credenciales(long id, String usuario, String password, long id_persona) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.id_persona = id_persona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId_persona() {
		return id_persona;
	}

	public void setId_persona(long id_persona) {
		this.id_persona = id_persona;
	}
}
