package com.pelayora.tarea3dwes.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fitosanitario")
public class Fitosanitario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_fitosanitario;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "marca", length = 100)
	private String marca;
	
	@Column(name = "eco")
	private boolean eco;
	
    @JoinTable(
        name = "fitosanitario_ejemplar",
        joinColumns = @JoinColumn(name = "id_fitosanitario"),
        inverseJoinColumns = @JoinColumn(name = "id_ejemplar")
    )
	@ManyToMany(cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares;

	public Fitosanitario(String nombre, String marca, boolean eco) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.eco = eco;
	}

	public Fitosanitario(Long id_fitosanitario, String nombre, String marca, boolean eco, List<Ejemplar> ejemplares) {
		super();
		this.id_fitosanitario = id_fitosanitario;
		this.nombre = nombre;
		this.marca = marca;
		this.eco = eco;
		this.ejemplares = ejemplares;
	}

	public Long getId_fitosanitario() {
		return id_fitosanitario;
	}

	public void setId_fitosanitario(Long id_fitosanitario) {
		this.id_fitosanitario = id_fitosanitario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isEco() {
		return eco;
	}

	public void setEco(boolean eco) {
		this.eco = eco;
	}
}
