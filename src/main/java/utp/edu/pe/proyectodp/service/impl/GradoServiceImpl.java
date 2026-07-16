package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Grado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.GradoRepository;
import utp.edu.pe.proyectodp.service.GradoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradoServiceImpl implements GradoService {

    private final GradoRepository repository;

    @Override
    public List<Grado> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Grado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Grado guardar(Grado grado) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(grado);
    }

    @Override
    public Grado actualizar(Long id, Grado grado) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoGrado(grado.getCodigoGrado());
                    registro.setNombreGrado(grado.getNombreGrado());
                    registro.setNivel(grado.getNivel());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Grado no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}