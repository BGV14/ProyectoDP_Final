package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Persona;
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
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}