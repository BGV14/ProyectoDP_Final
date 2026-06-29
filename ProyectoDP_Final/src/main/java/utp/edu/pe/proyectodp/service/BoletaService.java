package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Boleta;

import java.util.List;
import java.util.Optional;

public interface BoletaService {

    List<Boleta> listar();

    Optional<Boleta> buscarPorId(Long id);

    Boleta guardar(Boleta boleta);

    Boleta actualizar(Long id, Boleta boleta);

    void eliminar(Long id);
}
