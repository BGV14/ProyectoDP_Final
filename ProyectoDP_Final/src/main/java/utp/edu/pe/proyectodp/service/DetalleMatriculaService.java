package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.DetalleMatricula;

import java.util.List;
import java.util.Optional;

public interface DetalleMatriculaService {

    List<DetalleMatricula> listar();

    Optional<DetalleMatricula> buscarPorId(Long id);

    DetalleMatricula guardar(DetalleMatricula detalleMatricula);

    DetalleMatricula actualizar(Long id, DetalleMatricula detalleMatricula);

    void eliminar(Long id);
}