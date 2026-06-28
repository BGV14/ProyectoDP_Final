package utp.edu.pe.proyectodp.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Asistencia {

    private double porcentaje;
    private int faltas;
    private int tardanzas;
}
