package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Distrito;

import java.util.List;
import java.util.Optional;

public interface DistritoService {

    List<Distrito> listar();

    Optional<Distrito> buscarPorId(Long id);

    Distrito guardar(Distrito distrito);

    Distrito actualizar(Long id, Distrito distrito);

    void eliminar(Long id);
}
