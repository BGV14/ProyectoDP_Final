package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Seccion;
import utp.edu.pe.proyectodp.repository.SeccionRepository;
import utp.edu.pe.proyectodp.service.SeccionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeccionServiceImpl implements SeccionService {

    private final SeccionRepository repository;

    @Override
    public List<Seccion> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Seccion> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Seccion guardar(Seccion seccion) {
        return repository.save(seccion);
    }

    @Override
    public Seccion actualizar(Long id, Seccion seccion) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoSeccion(seccion.getCodigoSeccion());
                    registro.setNombreSeccion(seccion.getNombreSeccion());
                    registro.setCapacidad(seccion.getCapacidad());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Sección no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}