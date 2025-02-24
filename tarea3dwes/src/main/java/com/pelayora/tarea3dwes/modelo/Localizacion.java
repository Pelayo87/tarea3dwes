package com.pelayora.tarea3dwes.modelo;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-01-16
//Descripción: Clase VO de localizacion.
//--------------------------------------------------------

@Entity
@Table(name = "localizacion")
public class Localizacion implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacion")
    private long id_localizacion;

    @Column(name = "numseccion")
    private int numSeccion;

    @Column(name = "mesa")
    private char mesa;

    @Column(name = "exterior")
    private boolean exterior;

    @OneToOne(mappedBy = "localizacion", cascade = CascadeType.ALL)
    private Ejemplar ejemplar;

    @ManyToOne
    @JoinColumn(name = "id_seccion", nullable = false)
    private Seccion seccion;

   
    public long getIdLocalizacion() {
        return id_localizacion;
    }

    public void setIdLocalizacion(long idLocalizacion) {
        this.id_localizacion = idLocalizacion;
    }

    public int getNumSeccion() {
        return numSeccion;
    }

    public void setNumSeccion(int numSeccion) {
        this.numSeccion = numSeccion;
    }

    public char getMesa() {
        return mesa;
    }

    public void setMesa(char mesa) {
        this.mesa = mesa;
    }

    public boolean isExterior() {
        return exterior;
    }

    public void setExterior(boolean exterior) {
        this.exterior = exterior;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

	@Override
	public String toString() {
		return "Localizacion [id_localizacion=" + id_localizacion + ", numSeccion=" + numSeccion + ", mesa=" + mesa
				+ ", exterior=" + exterior + ", ejemplar=" + ejemplar + ", seccion=" + seccion + "]";
	} 
}
