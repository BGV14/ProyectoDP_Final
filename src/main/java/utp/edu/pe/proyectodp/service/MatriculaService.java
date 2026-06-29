package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    List<Matricula> listar();

    Optional<Matricula> buscarPorId(Long id);

    Matricula guardar(Matricula matricula);

    Matricula actualizar(Long id, Matricula matricula);

    void eliminar(Long id);
}