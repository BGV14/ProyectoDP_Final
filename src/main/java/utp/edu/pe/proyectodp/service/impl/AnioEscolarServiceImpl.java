package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.AnioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.AnioEscolarRepository;
import utp.edu.pe.proyectodp.service.AnioEscolarService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnioEscolarServiceImpl implements AnioEscolarService {

    private final AnioEscolarRepository repository;

    @Override
    public List<AnioEscolar> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<AnioEscolar> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public AnioEscolar guardar(AnioEscolar anioEscolar) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(anioEscolar);
    }

    @Override
    public AnioEscolar actualizar(Long id, AnioEscolar anioEscolar) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoAnio(anioEscolar.getCodigoAnio());
                    registro.setAnio(anioEscolar.getAnio());
                    registro.setFechaInicio(anioEscolar.getFechaInicio());
                    registro.setFechaFin(anioEscolar.getFechaFin());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("AÃ±o Escolar no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}