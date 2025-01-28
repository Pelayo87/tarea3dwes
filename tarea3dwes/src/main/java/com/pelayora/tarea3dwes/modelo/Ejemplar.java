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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Clase VO de ejemplares.
//--------------------------------------------------------

@Entity
@Table(name = "ejemplares")
public class Ejemplar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ejemplar;
	
	@Column(name = "nombrecomun", length = 50)
	private String nombre;
	
	@ManyToOne(cascade= CascadeType.REMOVE)
	@JoinColumn(name="id_planta")
	private Planta planta;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_ejemplar")
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();
	
	@ManyToMany(mappedBy = "ejemplares")
    private List<Fitosanitario> fitosanitarios;
	
	@OneToOne(mappedBy = "ejemplar", cascade = CascadeType.ALL)
    private Historial historial;
	
	@OneToOne
    @JoinColumn(name = "id_localizacion", unique=true)
    private Localizacion localizacion;

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

	public Ejemplar(Long id_ejemplar, String nombre, Planta planta, List<Mensaje> mensajes,
			List<Fitosanitario> fitosanitarios, Historial historial) {
		super();
		this.id_ejemplar = id_ejemplar;
		this.nombre = nombre;
		this.planta = planta;
		this.mensajes = mensajes;
		this.fitosanitarios = fitosanitarios;
		this.historial = historial;
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

	public List<Fitosanitario> getFitosanitarios() {
		return fitosanitarios;
	}

	public void setFitosanitarios(List<Fitosanitario> fitosanitarios) {
		this.fitosanitarios = fitosanitarios;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public Long getId_ejemplar() {
		return id_ejemplar;
	}

	public void setId_ejemplar(Long id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	@Override
	public String toString() {
		return "Ejemplar [id_ejemplar=" + id_ejemplar + ", nombre=" + nombre + ", planta=" + planta;
	}
}