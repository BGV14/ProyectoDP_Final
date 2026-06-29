package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Grado;

import java.util.List;
import java.util.Optional;

public interface GradoService {

    List<Grado> listar();

    Optional<Grado> buscarPorId(Long id);

    Grado guardar(Grado grado);

    Grado actualizar(Long id, Grado grado);

    void eliminar(Long id);
}