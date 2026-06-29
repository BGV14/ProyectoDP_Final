package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Grado;
import utp.edu.pe.proyectodp.repository.GradoRepository;
import utp.edu.pe.proyectodp.service.GradoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradoServiceImpl implements GradoService {

    private final GradoRepository repository;

    @Override
    public List<Grado> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Grado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Grado guardar(Grado grado) {
        return repository.save(grado);
    }

    @Override
    public Grado actualizar(Long id, Grado grado) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoGrado(grado.getCodigoGrado());
                    registro.setNombreGrado(grado.getNombreGrado());
                    registro.setNivel(grado.getNivel());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Grado no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}