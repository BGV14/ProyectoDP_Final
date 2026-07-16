package utp.edu.pe.proyectodp.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.repository.AnioEscolarRepository;
import utp.edu.pe.proyectodp.service.pattern.singlenton.AnioEscolarSingleton;

@Component
@RequiredArgsConstructor
public class AnioEscolarInitializer {

    private final AnioEscolarRepository anioEscolarRepository;
    @PostConstruct
    public void inicializar() {
        anioEscolarRepository.findAll().stream()
                .findFirst()
                .ifPresent(anio -> {
                    var singleton = AnioEscolarSingleton.getInstancia();
                    singleton.setAnio(anio.getAnio());
                    singleton.setFechaInicio(anio.getFechaInicio());
                    singleton.setFechaFin(anio.getFechaFin());
                });
    }
}
