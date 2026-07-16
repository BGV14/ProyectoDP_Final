package utp.edu.pe.proyectodp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import utp.edu.pe.proyectodp.entity.Matricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.repository.MatriculaRepository;
import utp.edu.pe.proyectodp.service.MatriculaService;
import utp.edu.pe.proyectodp.service.pattern.factory.MatriculaFactory;
import utp.edu.pe.proyectodp.service.pattern.observer.MatriculaConfirmadaEvent;
import utp.edu.pe.proyectodp.service.pattern.singlenton.AnioEscolarSingleton;
import utp.edu.pe.proyectodp.service.pattern.singlenton.ConfiguracionSistema;
import utp.edu.pe.proyectodp.service.pattern.singlenton.SesionSistema;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final MatriculaFactory matriculaFactory;

    @Override
    public List<Matricula> listar() {
        log.info("Consultando la lista completa de matrículas");
        return repository.findAll();
    }

    @Override
    public Optional<Matricula> buscarPorId(Long id) {
        log.info("Buscando matrícula con ID: {}", id);
        return repository.findById(id);
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        var config = ConfiguracionSistema.getInstancia();
        if (config.isMantenimiento()) {
            throw new IllegalStateException("El sistema está en mantenimiento. Intente más tarde.");
        }

        log.info("Guardando nueva matrícula para el código: {}", matricula.getCodigoMatricula());

        var anioActivo = AnioEscolarSingleton.getInstancia();
        if (!anioActivo.isActivo()) {
            throw new IllegalStateException("No hay un año escolar activo configurado");
        }
        log.info("Registrando matrícula para el año escolar: {}", anioActivo.getAnio());

        var sesion = SesionSistema.getInstancia();
        if (!sesion.isAutenticado()) {
            throw new IllegalStateException("Debe iniciar sesión para registrar una matrícula");
        }
        log.info("Matrícula registrada por el usuario: {}", sesion.getUsuario());

        var procesador = matriculaFactory.crearMatricula(matricula.getTipoMatricula());
        procesador.procesarMatricula();

        Matricula guardada = repository.save(matricula);
        eventPublisher.publishEvent(new MatriculaConfirmadaEvent(this, guardada));

        return guardada;
    }

    @Override
    public Matricula actualizar(Long id, Matricula matricula) {
        log.info("Actualizando matrícula con ID: {}", id);
        return repository.findById(id)
                .map(registro -> {
                    registro.setCodigoMatricula(matricula.getCodigoMatricula());
                    registro.setFechaMatricula(matricula.getFechaMatricula());
                    registro.setTurno(matricula.getTurno());
                    registro.setTipoMatricula(matricula.getTipoMatricula());
                    return repository.save(registro);
                })
                .orElseThrow(() -> new RecursoNoEncontradoException("Matrícula no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando matrícula con ID: {}", id);
        repository.deleteById(id);
    }
}