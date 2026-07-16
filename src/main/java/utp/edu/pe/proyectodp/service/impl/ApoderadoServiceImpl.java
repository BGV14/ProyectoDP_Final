package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Apoderado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.ApoderadoRepository;
import utp.edu.pe.proyectodp.service.ApoderadoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApoderadoServiceImpl implements ApoderadoService {
    private final ApoderadoRepository repository;

    @Override
    public List<Apoderado> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Apoderado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Apoderado guardar(Apoderado apoderado) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(apoderado);
    }

    @Override
    public Apoderado actualizar(Long id, Apoderado apoderado) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoApoderado(apoderado.getCodigoApoderado());
                    registro.setNombre(apoderado.getNombre());
                    registro.setApellido(apoderado.getApellido());
                    registro.setDni(apoderado.getDni());
                    registro.setFechaNacimiento(apoderado.getFechaNacimiento());
                    registro.setDireccion(apoderado.getDireccion());
                    registro.setTelefono(apoderado.getTelefono());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Apoderado no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}