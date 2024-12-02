package com.pelayora.tarea3dwes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pelayora.tarea3dwes.modelo.Credenciales;
import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;
import com.pelayora.tarea3dwes.servicios.ServicioCredenciales;

@Service
public class ServicioCredencialesImpl implements ServicioCredenciales {

	@Autowired
    private CredencialesRepository credencialesRepository;

    @Override
    public List<Credenciales> listarCredenciales() {
        return credencialesRepository.findAll();
    }

    @Override
    public Optional<Credenciales> buscarPorId(long id) {
        return credencialesRepository.findById(id);
    }

    @Override
    public Optional<Credenciales> buscarPorUsuario(String usuario) {
        return credencialesRepository.findByUsuario(usuario);
    }

    @Override
    public Credenciales guardarCredenciales(Credenciales credenciales) {
        return credencialesRepository.save(credenciales);
    }

    @Override
    public void eliminarCredenciales(long id) {
        credencialesRepository.deleteById(id);
    }
    
    @Override
    public boolean autenticar(Credenciales credenciales) {
        Optional<Credenciales> credencialesDB = buscarPorUsuario(credenciales.getUsuario());
        return credencialesDB.isPresent() && 
               credencialesDB.get().getPassword().equals(credenciales.getPassword());
    }

}
