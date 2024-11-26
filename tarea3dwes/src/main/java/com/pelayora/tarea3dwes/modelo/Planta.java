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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plantas")
public class Planta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nombrecomun", nullable = false, length = 50)
	private String nombrecomun;
	
	@Column(name = "nombrecientifico", nullable = false, length = 50)
    private String nombrecientifico;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idplanta")
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	
	public Planta() {
		super();
	}

	public Planta(Long codigo, String nombrecomun, String nombrecientifico, List<Ejemplar> ejemplares) {
		super();
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
		this.ejemplares = ejemplares;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
}