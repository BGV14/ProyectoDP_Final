package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Curso;
import utp.edu.pe.proyectodp.repository.CursoRepository;
import utp.edu.pe.proyectodp.service.CursoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;

    @Override
    public List<Curso> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public Curso actualizar(Long id, Curso curso) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoCurso(curso.getCodigoCurso());
                    registro.setNombre(curso.getNombre());
                    registro.setHoras(curso.getHoras());
                    registro.setGrado(curso.getGrado());
                    registro.setNivel(curso.getNivel());
                    registro.setArea(curso.getArea());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}