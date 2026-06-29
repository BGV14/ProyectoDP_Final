package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.AnioEscolar;

import java.util.List;
import java.util.Optional;

public interface AnioEscolarService {

    List<AnioEscolar> listar();

    Optional<AnioEscolar> buscarPorId(Long id);

    AnioEscolar guardar(AnioEscolar anioEscolar);

    AnioEscolar actualizar(Long id, AnioEscolar anioEscolar);

    void eliminar(Long id);
}
