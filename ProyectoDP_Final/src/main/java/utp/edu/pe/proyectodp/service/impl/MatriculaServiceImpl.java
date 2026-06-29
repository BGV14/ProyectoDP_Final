package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Matricula;
import utp.edu.pe.proyectodp.repository.MatriculaRepository;
import utp.edu.pe.proyectodp.service.MatriculaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;

    @Override
    public List<Matricula> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        return repository.save(matricula);
    }

    @Override
    public Matricula actualizar(Long id, Matricula matricula) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoMatricula(matricula.getCodigoMatricula());
                    registro.setFechaMatricula(matricula.getFechaMatricula());
                    registro.setTurno(matricula.getTurno());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}