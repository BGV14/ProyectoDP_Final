package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.DetalleMatricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

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
                .orElseThrow(() -> new RecursoNoEncontradoException("Detalle de MatrÃ­cula no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}