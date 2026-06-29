package utp.edu.pe.proyectodp.service;

import utp.edu.pe.proyectodp.entity.CalendarioEscolar;

import java.util.List;
import java.util.Optional;

public interface CalendarioEscolarService {

    List<CalendarioEscolar> listar();

    Optional<CalendarioEscolar> buscarPorId(Long id);

    CalendarioEscolar guardar(CalendarioEscolar calendarioEscolar);

    CalendarioEscolar actualizar(Long id, CalendarioEscolar calendarioEscolar);

    void eliminar(Long id);
}
