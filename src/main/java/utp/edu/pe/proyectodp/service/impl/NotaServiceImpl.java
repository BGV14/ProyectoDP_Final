package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Nota;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.NotaRepository;
import utp.edu.pe.proyectodp.service.NotaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;

    @Override
    public List<Nota> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Nota> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Nota guardar(Nota nota) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(nota);
    }

    @Override
    public Nota actualizar(Long id, Nota nota) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNota1(nota.getNota1());
                    registro.setNota2(nota.getNota2());
                    registro.setNota3(nota.getNota3());
                    registro.setNota4(nota.getNota4());
                    registro.setPromedio(nota.getPromedio());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Nota no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}