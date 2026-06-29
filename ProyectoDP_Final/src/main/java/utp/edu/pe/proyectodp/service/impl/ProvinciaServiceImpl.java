package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Provincia;
import utp.edu.pe.proyectodp.repository.ProvinciaRepository;
import utp.edu.pe.proyectodp.service.ProvinciaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProvinciaServiceImpl implements ProvinciaService {

    private final ProvinciaRepository repository;

    @Override
    public List<Provincia> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Provincia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Provincia guardar(Provincia provincia) {
        return repository.save(provincia);
    }

    @Override
    public Provincia actualizar(Long id, Provincia provincia) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreProvincia(provincia.getNombreProvincia());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}