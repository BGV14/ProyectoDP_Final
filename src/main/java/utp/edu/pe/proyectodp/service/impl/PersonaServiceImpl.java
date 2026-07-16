package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Persona;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.PersonaRepository;
import utp.edu.pe.proyectodp.service.PersonaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repository;

    @Override
    public List<Persona> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Persona> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Persona guardar(Persona persona) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(persona);
    }

    @Override
    public Persona actualizar(Long id, Persona persona) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombre(persona.getNombre());
                    registro.setApellido(persona.getApellido());
                    registro.setDni(persona.getDni());
                    registro.setFechaNacimiento(persona.getFechaNacimiento());
                    registro.setDireccion(persona.getDireccion());
                    registro.setTelefono(persona.getTelefono());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Persona no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}