package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Provincia;

import java.util.List;
import java.util.Optional;

public interface ProvinciaService {

    List<Provincia> listar();

    Optional<Provincia> buscarPorId(Long id);

    Provincia guardar(Provincia provincia);

    Provincia actualizar(Long id, Provincia provincia);

    void eliminar(Long id);
}
