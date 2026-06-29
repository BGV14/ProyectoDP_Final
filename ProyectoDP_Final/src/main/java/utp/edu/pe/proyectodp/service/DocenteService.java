package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {

    List<Docente> listar();

    Optional<Docente> buscarPorId(Long id);

    Docente guardar(Docente docente);

    Docente actualizar(Long id, Docente docente);

    void eliminar(Long id);
}
