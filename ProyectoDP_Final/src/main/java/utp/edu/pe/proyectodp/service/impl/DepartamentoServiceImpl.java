package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Departamento;
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
        return repository.save(departamento);
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreDepartamento(departamento.getNombreDepartamento());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}