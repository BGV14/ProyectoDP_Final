package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.CalendarioEscolar;
import utp.edu.pe.proyectodp.repository.CalendarioEscolarRepository;
import utp.edu.pe.proyectodp.service.CalendarioEscolarService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarioEscolarServiceImpl implements CalendarioEscolarService {

    private final CalendarioEscolarRepository repository;

    @Override
    public List<CalendarioEscolar> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<CalendarioEscolar> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public CalendarioEscolar guardar(CalendarioEscolar calendarioEscolar) {
        return repository.save(calendarioEscolar);
    }

    @Override
    public CalendarioEscolar actualizar(Long id, CalendarioEscolar calendarioEscolar) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setNombreEvento(calendarioEscolar.getNombreEvento());
                    registro.setDescripcion(calendarioEscolar.getDescripcion());
                    registro.setFechaInicio(calendarioEscolar.getFechaInicio());
                    registro.setFechaFin(calendarioEscolar.getFechaFin());
                    registro.setTipoEvento(calendarioEscolar.getTipoEvento());
                    registro.setEstado(calendarioEscolar.getEstado());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Calendario Escolar no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}