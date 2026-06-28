package utp.edu.pe.proyectodp.model;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Docente {

    private int idDocente;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String correo;

}
