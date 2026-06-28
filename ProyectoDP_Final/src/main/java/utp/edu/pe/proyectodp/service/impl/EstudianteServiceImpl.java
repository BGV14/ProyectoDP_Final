package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Estudiante;
import utp.edu.pe.proyectodp.repository.EstudianteRepository;
import utp.edu.pe.proyectodp.service.EstudianteService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository repository;

    @Override
    public List<Estudiante> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Estudiante> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Long id, Estudiante estudiante) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoEstudiante(estudiante.getCodigoEstudiante());
                    registro.setEstado(estudiante.getEstado());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}