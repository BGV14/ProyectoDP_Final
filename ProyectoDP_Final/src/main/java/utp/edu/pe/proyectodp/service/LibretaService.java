package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Libreta;

import java.util.List;
import java.util.Optional;

public interface LibretaService {

    List<Libreta> listar();

    Optional<Libreta> buscarPorId(Long id);

    Libreta guardar(Libreta libreta);

    Libreta actualizar(Long id, Libreta libreta);

    void eliminar(Long id);
}