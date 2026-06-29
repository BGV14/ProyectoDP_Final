package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Evaluacion;

import java.util.List;
import java.util.Optional;

public interface EvaluacionService {

    List<Evaluacion> listar();

    Optional<Evaluacion> buscarPorId(Long id);

    Evaluacion guardar(Evaluacion evaluacion);

    Evaluacion actualizar(Long id, Evaluacion evaluacion);

    void eliminar(Long id);
}