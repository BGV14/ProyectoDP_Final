package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Aula;

import java.util.List;
import java.util.Optional;

public interface AulaService {

    List<Aula> listar();

    Optional<Aula> buscarPorId(Long id);

    Aula guardar(Aula aula);

    Aula actualizar(Long id, Aula aula);

    void eliminar(Long id);
}