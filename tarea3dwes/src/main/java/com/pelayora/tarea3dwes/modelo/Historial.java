package com.pelayora.tarea3dwes.modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-01-12
//Descripción: Clase VO de historiales.
//--------------------------------------------------------

@Entity
@Table(name = "historial")
public class Historial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_historial;
	
	@Column(name = "nh", length = 50, unique = true)
	private String nh;
	
	@Column(name = "actualizado")
    private LocalDate actualizado;
	
	@OneToOne
    @JoinColumn(name = "id_ejemplar")
    private Ejemplar ejemplar;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_historial")
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();

	public Historial() {
		super();
	}

	public Historial(Long id_historial, String nh, LocalDate actualizado) {
		super();
		this.id_historial = id_historial;
		this.nh = nh;
		this.actualizado = actualizado;
	}

	public Historial(Long id_historial, String nh, LocalDate actualizado, Ejemplar ejemplar) {
		super();
		this.id_historial = id_historial;
		this.nh = nh;
		this.actualizado = actualizado;
		this.ejemplar = ejemplar;
	}

	public Long getId_historial() {
		return id_historial;
	}

	public void setId_historial(Long id_historial) {
		this.id_historial = id_historial;
	}

	public String getNh() {
		return nh;
	}

	public void setNh(String nh) {
		this.nh = nh;
	}

	public LocalDate getActualizado() {
		return actualizado;
	}

	public void setActualizado(LocalDate actualizado) {
		this.actualizado = actualizado;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	@Override
	public String toString() {
		return "Historial [id_historial=" + id_historial + ", nh=" + nh + ", actualizado=" + actualizado + ", ejemplar="
				+ ejemplar + "]";
	}
}
