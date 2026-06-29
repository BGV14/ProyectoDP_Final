package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Nota;
import utp.edu.pe.proyectodp.repository.NotaRepository;
import utp.edu.pe.proyectodp.service.NotaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;

    @Override
    public List<Nota> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Nota> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Nota guardar(Nota nota) {
        return repository.save(nota);
    }

    @Override
    public Nota actualizar(Long id, Nota nota) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNota1(nota.getNota1());
                    registro.setNota2(nota.getNota2());
                    registro.setNota3(nota.getNota3());
                    registro.setNota4(nota.getNota4());
                    registro.setPromedio(nota.getPromedio());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}