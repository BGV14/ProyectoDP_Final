package utp.edu.pe.proyectodp.service.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import utp.edu.pe.proyectodp.service.pattern.factory.interfaces.Matricula;

@Slf4j
public class MatriculaRegular implements Matricula {

    @Override
    public void procesarMatricula() {
        log.info("Procesando matrícula regular: Validando promedio y generando recibo estándar.");
    }
}



