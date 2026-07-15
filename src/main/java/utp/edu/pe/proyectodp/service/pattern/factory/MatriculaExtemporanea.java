package utp.edu.pe.proyectodp.service.pattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatriculaExtemporanea implements Matricula{

    @Override
    public void procesarMatricula() {
        log.info("Procesando matrícula extemporánea: Aplicando recargo por extemporaneidad y validando autorización extemporánea.");
    }
}
