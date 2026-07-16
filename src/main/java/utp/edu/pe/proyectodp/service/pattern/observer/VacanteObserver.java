package utp.edu.pe.proyectodp.service.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VacanteObserver {

    @EventListener
    public void onMatriculaConfirmada(MatriculaConfirmadaEvent event) {
        var matricula = event.getMatricula();
        log.info("OBSERVER [Vacantes]: Actualizando disponibilidad de aulas para el turno: {}",
                matricula.getTurno());
    }
}