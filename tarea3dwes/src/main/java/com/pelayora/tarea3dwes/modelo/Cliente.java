package com.pelayora.tarea3dwes.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//--------------------------------------------------------
//Autor: Pelayo Rodríguez Álvarez
//Fecha: 2025-01-12
//Descripción: Clase VO de clientes.
//--------------------------------------------------------

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

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
	
	@Column(name = "direccionEnvio", length = 255, nullable = false)
    private String direccionEnvio;
    
    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "plantas_clientes",
	    joinColumns = @JoinColumn(name = "id_cliente"),
	    inverseJoinColumns = @JoinColumn(name = "codigo")
	)
	private List<Planta> plantas = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido> pedidos;


	public Cliente() {
		super();
	}

	public Cliente(Long id_cliente, String nombre, String nif_nie, LocalDate fechaNacimiento, LocalDate fechaRegistro,
			String direccionEnvio, String telefono, String email, List<Planta> plantas) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.nif_nie = nif_nie;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.direccionEnvio = direccionEnvio;
		this.telefono = telefono;
		this.email = email;
		this.plantas = plantas;
	}

	public Cliente(Long id_cliente, String nombre, String nif_nie, LocalDate fechaNacimiento, LocalDate fechaRegistro,
			String direccionEnvio, String telefono, String email, List<Planta> plantas, List<Pedido> pedidos) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.nif_nie = nif_nie;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.direccionEnvio = direccionEnvio;
		this.telefono = telefono;
		this.email = email;
		this.plantas = plantas;
		this.pedidos = pedidos;
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

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nombre=" + nombre + ", nif_nie=" + nif_nie
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaRegistro=" + fechaRegistro + "]";
	}
}
