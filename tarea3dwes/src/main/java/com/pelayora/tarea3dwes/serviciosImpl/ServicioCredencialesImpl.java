package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.modelo.Persona;
import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;
import com.pelayora.tarea3dwes.repositorios.PersonaRepository;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;

import jakarta.annotation.PostConstruct;

@Service
public class ServicioCredencialesImpl implements ServicioCredenciales {

	@Autowired
    private CredencialesRepository credenciales_R;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Credenciales> listarCredenciales() {
        return credenciales_R.findAll();
    }

    @Override
    public Optional<Credenciales> buscarPorId(long id) {
        return credenciales_R.findById(id);
    }

    @Override
    public Optional<Credenciales> buscarPorUsuario(String usuario) {
        return credenciales_R.findByUsuario(usuario);
    }
    
    @PostConstruct
    public void crearAdminSiNoExiste() {
        // Verificar si ya existe el administrador
        Optional<Credenciales> admin = credenciales_R.findByUsuario("admin");
        if (admin.isEmpty()) {
            // Crear un nuevo usuario administrador
            Persona personaAdmin = new Persona();
            personaAdmin.setNombre("Admin");
            personaAdmin.setEmail("admin@admin.com");
            personaAdmin = personaRepository.save(personaAdmin);

            Credenciales credencialesAdmin = new Credenciales();
            credencialesAdmin.setUsuario("admin");
            credencialesAdmin.setPassword(passwordEncoder.encode("admin"));
            credencialesAdmin.setPersona(personaAdmin);

            credenciales_R.save(credencialesAdmin);
            System.out.println("Administrador creado con éxito.");
        } else {
            System.out.println("El usuario administrador ya existe.");
        }
    }

    @Override
    public Credenciales guardarCredenciales(Credenciales credenciales) {
       if (credenciales.getPassword() == null || credenciales.getPassword().isEmpty()) {
    	        System.err.println("La contraseña no puede ser nula o vacía");
    	}

    	credenciales.setPassword(passwordEncoder.encode(credenciales.getPassword()));
        return credenciales_R.save(credenciales);
    }

    @Override
    public void eliminarCredenciales(long id) {
        credenciales_R.deleteById(id);
    }
    
    @Override
    public boolean autenticar(Credenciales credenciales) {
        Optional<Credenciales> credencialesDB = buscarPorUsuario(credenciales.getUsuario());
        return credencialesDB.isPresent() && 
               credencialesDB.get().getPassword().equals(credenciales.getPassword());
    }
}
