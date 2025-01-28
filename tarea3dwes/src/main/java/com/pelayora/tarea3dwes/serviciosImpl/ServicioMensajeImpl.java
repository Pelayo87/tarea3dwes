package com.pelayora.tarea3dwes.serviciosImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pelayora.tarea3dwes.modelo.Mensaje;
import com.pelayora.tarea3dwes.repositorios.MensajeRepository;
import com.pelayora.tarea3dwes.servicios.ServicioMensaje;

@Service
public class ServicioMensajeImpl implements ServicioMensaje {
	
	static Scanner sc = new Scanner(System.in);
	
	// FECHA ACTUAL Y FORMATEADA
	java.util.Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	String fechaFormateada = formatoFecha.format(fechaActual);

	@Autowired
	private MensajeRepository mensajeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Mensaje> listarMensajes() {
		return mensajeRepository.findAll();
	}

	@Override
	public Optional<Mensaje> buscarPorId(long id) {
		return mensajeRepository.findById(id);
	}

	@Override
	public List<Mensaje> buscarPorPersonaId(long personaId) {
		return mensajeRepository.findByPersonaId(personaId);
	}

	@Override
	public List<Mensaje> buscarPorEjemplarId(long ejemplarId) {
		return mensajeRepository.findByEjemplarId(ejemplarId);
	}

	@Override
	public Mensaje guardarMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}

	@Override
	public void eliminarMensaje(long id) {
		mensajeRepository.deleteById(id);
	}

	public void filtrarAnotacionesporRangoFecha() {
		try {
			// Ingreso y conversión de la primera fecha
			System.out.print("Fecha 1 (dd/MM/yyyy): ");
			java.util.Date fecha1 = formatoFecha.parse(sc.nextLine());
			LocalDateTime fecha1Local = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			// Ingreso y conversión de la segunda fecha
			System.out.print("Fecha 2 (dd/MM/yyyy): ");
			java.util.Date fecha2 = formatoFecha.parse(sc.nextLine());
			LocalDateTime fecha2Local = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withHour(23)
					.withMinute(59).withSecond(59);

			// Obtengo todos los mensajes
			List<Mensaje> listaMensajes = mensajeRepository.findAll();

			// Filtra los mensajes en el rango de fecha1 a fecha2
			Set<Mensaje> mensajesFiltrados = listaMensajes.stream().filter(mensaje -> {
				LocalDateTime fechaMensaje = mensaje.getFechahora().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				return !fechaMensaje.isBefore(fecha1Local) && !fechaMensaje.isAfter(fecha2Local);
			}).collect(Collectors.toSet());

			for (Mensaje m : mensajesFiltrados) {
				System.out.println("El dia " + m.getFechahora() + "fue realizado el siguiente mensaje/anotación:"
						+ m.getMensaje());
			}
		} catch (ParseException e) {
			System.err.println("Formato de fecha incorrecto. Usa el formato dd/MM/yyyy.");
			sc.nextLine();
		}
	}

	@Override
	public long contadorMensajes() {
		return mensajeRepository.contarMensajes();
	}

}
