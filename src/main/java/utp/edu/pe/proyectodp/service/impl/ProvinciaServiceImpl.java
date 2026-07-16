package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Provincia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(provincia);
    }

    @Override
    public Provincia actualizar(Long id, Provincia provincia) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreProvincia(provincia.getNombreProvincia());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Provincia no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}