package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Asistencia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.AsistenciaRepository;
import utp.edu.pe.proyectodp.service.AsistenciaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository repository;

    @Override
    public List<Asistencia> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Asistencia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Asistencia guardar(Asistencia asistencia) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(asistencia);
    }

    @Override
    public Asistencia actualizar(Long id, Asistencia asistencia) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setPorcentaje(asistencia.getPorcentaje());
                    registro.setFaltas(asistencia.getFaltas());
                    registro.setTardanzas(asistencia.getTardanzas());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Asistencia no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}