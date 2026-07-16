package utp.edu.pe.proyectodp.service.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmailObserver {

    @EventListener
    public void onMatriculaConfirmada(MatriculaConfirmadaEvent event) {
        var matricula = event.getMatricula();
        log.info("OBSERVER [Email]: Enviando constancia de confirmación para la matrícula código: {}",
                matricula.getCodigoMatricula());
    }
}
