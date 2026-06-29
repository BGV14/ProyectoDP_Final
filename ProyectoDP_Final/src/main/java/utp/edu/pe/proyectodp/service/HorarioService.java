package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioService {

    List<Horario> listar();

    Optional<Horario> buscarPorId(Long id);

    Horario guardar(Horario horario);

    Horario actualizar(Long id, Horario horario);

    void eliminar(Long id);
}