package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Administrador;

import java.util.List;
import java.util.Optional;

public interface AdministradorService {

    List<Administrador> listar();

    Optional<Administrador> buscarPorId(Long id);

    Administrador guardar(Administrador administrador);

    Administrador actualizar(Long id, Administrador administrador);

    void eliminar(Long id);
}