package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Director;
import utp.edu.pe.proyectodp.repository.DirectorRepository;
import utp.edu.pe.proyectodp.service.DirectorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository repository;

    @Override
    public List<Director> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Director> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Director guardar(Director director) {
        return repository.save(director);
    }

    @Override
    public Director actualizar(Long id, Director director) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoDirector(director.getCodigoDirector());
                    registro.setOficina(director.getOficina());
                    registro.setCorreo(director.getCorreo());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}