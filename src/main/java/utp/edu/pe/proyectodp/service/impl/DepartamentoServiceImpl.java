package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Departamento;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.DepartamentoRepository;
import utp.edu.pe.proyectodp.service.DepartamentoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository repository;

    @Override
    public List<Departamento> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Departamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

        return repository.save(departamento);
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreDepartamento(departamento.getNombreDepartamento());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Departamento no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}