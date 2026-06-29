package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.DetalleMatricula;
import utp.edu.pe.proyectodp.repository.DetalleMatriculaRepository;
import utp.edu.pe.proyectodp.service.DetalleMatriculaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleMatriculaServiceImpl implements DetalleMatriculaService {
    private final DetalleMatriculaRepository repository;

    @Override
    public List<DetalleMatricula> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleMatricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public DetalleMatricula guardar(DetalleMatricula detalleMatricula) {
        return repository.save(detalleMatricula);
    }

    @Override
    public DetalleMatricula actualizar(Long id, DetalleMatricula detalleMatricula) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoDeMatricula(detalleMatricula.getCodigoDeMatricula());
                    registro.setFechaRegistro(detalleMatricula.getFechaRegistro());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Detalle de Matrícula no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}