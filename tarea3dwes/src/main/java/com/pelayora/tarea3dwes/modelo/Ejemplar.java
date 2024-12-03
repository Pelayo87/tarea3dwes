package com.pelayora.tarea3dwes.modelo;

import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplares")
public class Ejemplar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ejemplar;
	
	@Column(name = "nombrecomun", length = 50)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_planta")
	private Planta planta;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_ejemplar")
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();

	public Ejemplar() {
		super();
	}

	public Ejemplar(Long id, String nombre, Planta planta, List<Mensaje> mensajes) {
		super();
		this.id_ejemplar = id;
		this.nombre = nombre;
		this.planta = planta;
		this.mensajes = mensajes;
	}

	public Long getId() {
		return id_ejemplar;
	}

	public void setId(Long id) {
		this.id_ejemplar = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	@Override
	public String toString() {
		return "Ejemplar [id_ejemplar=" + id_ejemplar + ", nombre=" + nombre + ", planta=" + planta + ", mensajes="
				+ mensajes + "]";
	}
}
