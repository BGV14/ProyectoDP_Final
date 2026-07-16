package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Evaluacion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.EvaluacionRepository;
import utp.edu.pe.proyectodp.service.EvaluacionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService {
    private final EvaluacionRepository repository;

    @Override
    public List<Evaluacion> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Evaluacion> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Evaluacion guardar(Evaluacion evaluacion) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(evaluacion);
    }

    @Override
    public Evaluacion actualizar(Long id, Evaluacion evaluacion) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setTipoEvaluacion(evaluacion.getTipoEvaluacion());
                    registro.setFechaEvaluacion(evaluacion.getFechaEvaluacion());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("EvaluaciÃ³n no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}