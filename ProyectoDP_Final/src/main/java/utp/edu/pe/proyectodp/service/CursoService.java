package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();

    Optional<Curso> buscarPorId(Long id);

    Curso guardar(Curso curso);

    Curso actualizar(Long id, Curso curso);

    void eliminar(Long id);
}