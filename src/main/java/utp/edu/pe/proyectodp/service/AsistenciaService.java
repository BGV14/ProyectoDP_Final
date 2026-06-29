package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Asistencia;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {

    List<Asistencia> listar();

    Optional<Asistencia> buscarPorId(Long id);

    Asistencia guardar(Asistencia asistencia);

    Asistencia actualizar(Long id, Asistencia asistencia);

    void eliminar(Long id);
}
