package com.pelayora.tarea3dwes.modelo;

import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2024-12-10
//Descripción: Clase VO de plantas.
//--------------------------------------------------------

@Entity
@Table(name = "plantas")
public class Planta {

	@Id
	@Column(name = "codigo", unique = true)
	private String codigo;

	@Column(name = "nombrecomun", length = 50)
	private String nombreComun;

	@Column(name = "nombrecientifico", length = 50)
	private String nombreCientifico;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_planta")
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();

	public Planta() {
		super();
	}	

	public Planta(String codigo, String nombreComun, String nombreCientifico) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}

    public Planta(String codigo, String nombreComun, String nombreCientifico, List<Ejemplar> ejemplares) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.ejemplares = ejemplares;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
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

    @Override
    public String toString() {
        return "Planta {" +
               "codigo='" + codigo + '\'' +
               ", nombreComun='" + nombreComun + '\'' +
               ", nombreCientifico='" + nombreCientifico + '\'' +
               '}';
    }

}