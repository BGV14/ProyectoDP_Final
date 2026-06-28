package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.MetodoPago;

import java.util.List;
import java.util.Optional;

public interface MetodoPagoService {

    List<MetodoPago> listar();

    Optional<MetodoPago> buscarPorId(Long id);

    MetodoPago guardar(MetodoPago metodoPago);

    MetodoPago actualizar(Long id, MetodoPago metodoPago);

    void eliminar(Long id);
}
