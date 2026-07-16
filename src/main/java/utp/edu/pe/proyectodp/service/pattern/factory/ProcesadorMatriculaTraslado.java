package utp.edu.pe.proyectodp.service.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import utp.edu.pe.proyectodp.service.pattern.factory.interfaces.ProcesadorMatricula;

@Slf4j
public class ProcesadorMatriculaTraslado implements ProcesadorMatricula {
    @Override
    public void procesarMatricula() {
        log.info("Procesando matrícula por traslado: Convalidando cursos de la otra universidad y aplicando tasa especial.");
    }
}