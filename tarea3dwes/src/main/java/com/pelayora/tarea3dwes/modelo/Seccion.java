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
@Table(name = "seccion")
public class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_seccion;
	
	@Column(name = "nombre", length = 50, unique =true)
	private String nombre;
	
	@Column(name = "area")
	private double area;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_seccion")
	private List<Localizacion> localizaciones = new LinkedList<Localizacion>();

	public Seccion() {
		super();
	}

	public Seccion(String nombre, double area) {
		super();
		this.nombre = nombre;
		this.area = area;
	}

	public Seccion(String nombre, double area, List<Localizacion> localizaciones) {
		super();
		this.nombre = nombre;
		this.area = area;
		this.localizaciones = localizaciones;
	}

	public Seccion(long id_seccion, String nombre, double area) {
		super();
		this.id_seccion = id_seccion;
		this.nombre = nombre;
		this.area = area;
	}

	public Seccion(long id_seccion, String nombre, double area, List<Localizacion> localizaciones) {
		super();
		this.id_seccion = id_seccion;
		this.nombre = nombre;
		this.area = area;
		this.localizaciones = localizaciones;
	}

	public long getId_seccion() {
		return id_seccion;
	}

	public void setId_seccion(long id_seccion) {
		this.id_seccion = id_seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public List<Localizacion> getLocalizaciones() {
		return localizaciones;
	}

	public void setLocalizaciones(List<Localizacion> localizaciones) {
		this.localizaciones = localizaciones;
	}

	@Override
	public String toString() {
		return "Seccion [id_seccion=" + id_seccion + ", nombre=" + nombre + ", area=" + area + ", localizaciones="
				+ localizaciones + "]";
	}
}
