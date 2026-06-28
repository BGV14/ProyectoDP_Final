package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Horario;
import utp.edu.pe.proyectodp.repository.HorarioRepository;
import utp.edu.pe.proyectodp.service.HorarioService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository repository;

    @Override
    public List<Horario> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Horario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Horario guardar(Horario horario) {
        return repository.save(horario);
    }

    @Override
    public Horario actualizar(Long id, Horario horario) {
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoHorario(horario.getCodigoHorario());
                    registro.setHoraInicio(horario.getHoraInicio());
                    registro.setHoraFin(horario.getHoraFin());
                    registro.setDia(horario.getDia());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}