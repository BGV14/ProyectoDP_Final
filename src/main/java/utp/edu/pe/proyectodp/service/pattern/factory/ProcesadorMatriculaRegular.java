package utp.edu.pe.proyectodp.service.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.factory.interfaces.ProcesadorMatricula;

@Slf4j
@Component
public class ProcesadorMatriculaRegular implements ProcesadorMatricula {
    @Override public String getTipo(){ return "REGULAR"; }
    @Override public void procesarMatricula() {
        log.info("Procesando matrícula regular: Validando promedio y generando recibo estándar.");
    }
}