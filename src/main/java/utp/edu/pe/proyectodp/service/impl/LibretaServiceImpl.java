package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Libreta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.LibretaRepository;
import utp.edu.pe.proyectodp.service.LibretaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibretaServiceImpl implements LibretaService {

    private final LibretaRepository repository;

    @Override
    public List<Libreta> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Libreta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Libreta guardar(Libreta libreta) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(libreta);
    }

    @Override
    public Libreta actualizar(Long id, Libreta libreta) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoLibreta(libreta.getCodigoLibreta());
                    registro.setFechaEmision(libreta.getFechaEmision());
                    registro.setObservacion(libreta.getObservacion());
                    registro.setPromedioFinal(libreta.getPromedioFinal());
                    registro.setEstado(libreta.getEstado());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Libreta no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}