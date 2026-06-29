package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> listar();

    Optional<Persona> buscarPorId(Long id);

    Persona guardar(Persona persona);

    Persona actualizar(Long id, Persona persona);

    void eliminar(Long id);
}
