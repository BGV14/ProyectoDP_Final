package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Seccion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

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
                .orElseThrow(() -> new RecursoNoEncontradoException("SecciÃ³n no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}