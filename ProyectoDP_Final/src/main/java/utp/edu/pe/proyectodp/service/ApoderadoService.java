package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Apoderado;

import java.util.List;
import java.util.Optional;

public interface ApoderadoService {

    List<Apoderado> listar();

    Optional<Apoderado> buscarPorId(Long id);

    Apoderado guardar(Apoderado apoderado);

    Apoderado actualizar(Long id, Apoderado apoderado);

    void eliminar(Long id);
}