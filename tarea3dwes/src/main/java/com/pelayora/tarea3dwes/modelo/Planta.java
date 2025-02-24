package com.pelayora.tarea3dwes.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Clase VO de plantas.
//--------------------------------------------------------

@Entity
@Table(name = "plantas")
public class Planta implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo", unique = true)
	private String codigo;

	@Column(name = "nombrecomun", length = 50)
	private String nombreComun;

	@Column(name = "nombrecientifico", length = 50)
	private String nombreCientifico;
	
	@Column(name = "precio")
	private double precio;
	
	@ManyToMany(mappedBy = "plantas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Cliente> clientes = new ArrayList<>();


	public Planta() {
		super();
	}	

	public Planta(String codigo, String nombreComun, String nombreCientifico) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}

	public Planta(String codigo, String nombreComun, String nombreCientifico, double precio, List<Cliente> clientes) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.precio = precio;
		this.clientes = clientes;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
    public String toString() {
        return "Planta {" +
               "codigo='" + codigo + '\'' +
               ", nombreComun='" + nombreComun + '\'' +
               ", nombreCientifico='" + nombreCientifico + '\'' +
               '}';
    }
}