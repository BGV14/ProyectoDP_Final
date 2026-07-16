package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Docente;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.DocenteRepository;
import utp.edu.pe.proyectodp.service.DocenteService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocenteServiceImpl implements DocenteService {
    private final DocenteRepository repository;

    @Override
    public List<Docente> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Docente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Docente guardar(Docente docente) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(docente);
    }

    @Override
    public Docente actualizar(Long id, Docente docente) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoDocente(docente.getCodigoDocente());
                    registro.setNombres(docente.getNombres());
                    registro.setApellidos(docente.getApellidos());
                    registro.setEspecialidad(docente.getEspecialidad());
                    registro.setCorreo(docente.getCorreo());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Docente no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}