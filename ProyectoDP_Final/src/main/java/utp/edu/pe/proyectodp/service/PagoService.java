package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> listar();

    Optional<Pago> buscarPorId(Long id);

    Pago guardar(Pago pago);

    Pago actualizar(Long id, Pago pago);

    void eliminar(Long id);
}
