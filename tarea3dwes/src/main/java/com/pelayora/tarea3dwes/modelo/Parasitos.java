package com.pelayora.tarea3dwes.modelo;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "parasitos")
public class Parasitos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_parasito;
	
	@Column(name = "nombre", length = 50, unique=true)
	private String nombre;
	
	@Column(name = "color", length = 50)
	private String color;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_parasito")
	private List<Enfermedad> enfermedades = new LinkedList<Enfermedad>();
	
	public Parasitos() {
		super();
	}

	public Parasitos(String nombre, String color) {
		super();
		this.nombre = nombre;
		this.color = color;
	}

	public Parasitos(Long id_parasito, String nombre, String color) {
		super();
		this.id_parasito = id_parasito;
		this.nombre = nombre;
		this.color = color;
	}

	public Parasitos(Long id_parasito, String nombre, String color, List<Enfermedad> enfermedades) {
		super();
		this.id_parasito = id_parasito;
		this.nombre = nombre;
		this.color = color;
		this.enfermedades = enfermedades;
	}

	public Long getId_parasito() {
		return id_parasito;
	}

	public void setId_parasito(Long id_parasito) {
		this.id_parasito = id_parasito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	@Override
	public String toString() {
		return "Id: " + id_parasito + " | Nombre: " + nombre + " | Color:" + color;
	}

}
