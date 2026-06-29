package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    List<Estudiante> listar();

    Optional<Estudiante> buscarPorId(Long id);

    Estudiante guardar(Estudiante estudiante);

    Estudiante actualizar(Long id, Estudiante estudiante);

    void eliminar(Long id);
}
