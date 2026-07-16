package utp.edu.pe.proyectodp.service.pattern.observer;

import org.springframework.context.ApplicationEvent;
import utp.edu.pe.proyectodp.entity.Matricula;

public class MatriculaConfirmadaEvent extends ApplicationEvent {

    private final transient Matricula matricula;

    public MatriculaConfirmadaEvent(Object source, Matricula matricula) {
        super(source);
        this.matricula = matricula;
    }

    public Matricula getMatricula() {
        return matricula;

    }
}