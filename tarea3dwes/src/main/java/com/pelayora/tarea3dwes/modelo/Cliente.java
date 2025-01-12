package com.pelayora.tarea3dwes.modelo;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-01-12
//Descripción: Clase VO de clientes.
//--------------------------------------------------------

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;

	@Column(name = "nombre", length = 50)
	private String nombre;

	@Column(name = "nif_nie", length = 10, unique = true)
	private String nif_nie;
	
	@Column(name = "fechaNacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "fechaRegistro")
	private LocalDate fechaRegistro;
	
	@ManyToMany(mappedBy = "clientes")
    private List<Planta> plantas;

	public Cliente() {
		super();
	}

	public Cliente(Long id_cliente, String nombre, String nif_nie, LocalDate fechaNacimiento, LocalDate fechaRegistro,
			List<Planta> plantas) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.nif_nie = nif_nie;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.plantas = plantas;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif_nie() {
		return nif_nie;
	}

	public void setNif_nie(String nif_nie) {
		this.nif_nie = nif_nie;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nombre=" + nombre + ", nif_nie=" + nif_nie
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaRegistro=" + fechaRegistro + "]";
	}
}
