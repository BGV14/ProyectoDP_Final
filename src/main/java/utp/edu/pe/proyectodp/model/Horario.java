package utp.edu.pe.proyectodp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Horario {
    private String codigoHorario;
    private String horaInicio;
    private String horaFin;
    private String dia;
}
