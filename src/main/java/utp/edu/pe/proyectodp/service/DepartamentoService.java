package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    List<Departamento> listar();

    Optional<Departamento> buscarPorId(Long id);

    Departamento guardar(Departamento departamento);

    Departamento actualizar(Long id, Departamento departamento);

    void eliminar(Long id);
}