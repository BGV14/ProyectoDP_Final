package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.CalendarioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.CalendarioEscolarRepository;
import utp.edu.pe.proyectodp.service.CalendarioEscolarService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarioEscolarServiceImpl implements CalendarioEscolarService {

    private final CalendarioEscolarRepository repository;

    @Override
    public List<CalendarioEscolar> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<CalendarioEscolar> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public CalendarioEscolar guardar(CalendarioEscolar calendarioEscolar) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(calendarioEscolar);
    }

    @Override
    public CalendarioEscolar actualizar(Long id, CalendarioEscolar calendarioEscolar) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreEvento(calendarioEscolar.getNombreEvento());
                    registro.setDescripcion(calendarioEscolar.getDescripcion());
                    registro.setFechaInicio(calendarioEscolar.getFechaInicio());
                    registro.setFechaFin(calendarioEscolar.getFechaFin());
                    registro.setTipoEvento(calendarioEscolar.getTipoEvento());
                    registro.setEstado(calendarioEscolar.getEstado());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Calendario Escolar no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}