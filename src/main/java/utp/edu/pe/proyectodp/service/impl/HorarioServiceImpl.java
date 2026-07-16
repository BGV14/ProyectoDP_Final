package utp.edu.pe.proyectodp.service.impl;

import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Horario;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
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
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para realizar esta operación");
        }

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
                .orElseThrow(() -> new RecursoNoEncontradoException("Horario no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}