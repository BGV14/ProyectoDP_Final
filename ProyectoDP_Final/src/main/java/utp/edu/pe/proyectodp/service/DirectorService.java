package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    List<Director> listar();

    Optional<Director> buscarPorId(Long id);

    Director guardar(Director director);

    Director actualizar(Long id, Director director);

    void eliminar(Long id);
}