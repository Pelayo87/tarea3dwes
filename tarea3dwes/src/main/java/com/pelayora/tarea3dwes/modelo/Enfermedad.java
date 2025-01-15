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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enfermedades")
public class Enfermedad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_enfermedad;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "sintomas", length = 50)
	private String sintomas;
	
	@Column(name = "nociva")
	private boolean nociva;
	
	@ManyToMany(mappedBy = "enfermedades", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Planta> plantas = new LinkedList<Planta>();
	
	@ManyToOne
	@JoinColumn(name="id_parasito", nullable=true)
	private Parasitos parasitos;
	
	public Enfermedad() {
		super();
	}

	public Enfermedad(String nombre, String sintomas, boolean nociva) {
		super();
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.nociva = nociva;
	}

	public Enfermedad(Long id_enfermedad, String nombre, String sintomas, boolean nociva) {
		super();
		this.id_enfermedad = id_enfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.nociva = nociva;
	}

	public Enfermedad(Long id_enfermedad, String nombre, String sintomas, boolean nociva, List<Planta> plantas,
			Parasitos parasitos) {
		super();
		this.id_enfermedad = id_enfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.nociva = nociva;
		this.plantas = plantas;
		this.parasitos = parasitos;
	}

	public Long getId_enfermedad() {
		return id_enfermedad;
	}

	public void setId_enfermedad(Long id_enfermedad) {
		this.id_enfermedad = id_enfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public boolean isNociva() {
		return nociva;
	}

	public void setNociva(boolean nociva) {
		this.nociva = nociva;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public Parasitos getParasitos() {
		return parasitos;
	}

	public void setParasitos(Parasitos parasitos) {
		this.parasitos = parasitos;
	}

	@Override
	public String toString() {
		return "Id: " + id_enfermedad + " | Nombre:" + nombre + " | Sintomas: " + sintomas + " | Nociva:" + nociva;
	}
}
