package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Seccion;

import java.util.List;
import java.util.Optional;

public interface SeccionService {

    List<Seccion> listar();

    Optional<Seccion> buscarPorId(Long id);

    Seccion guardar(Seccion seccion);

    Seccion actualizar(Long id, Seccion seccion);

    void eliminar(Long id);
}
