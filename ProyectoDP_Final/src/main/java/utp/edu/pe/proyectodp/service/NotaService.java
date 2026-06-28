package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Nota;

import java.util.List;
import java.util.Optional;

public interface NotaService {

    List<Nota> listar();

    Optional<Nota> buscarPorId(Long id);

    Nota guardar(Nota nota);

    Nota actualizar(Long id, Nota nota);

    void eliminar(Long id);
}
