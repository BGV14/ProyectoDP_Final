package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "docente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int codigoDocente;

    private String nombres;
    private String apellidos;
    private String especialidad;
    private String correo;

}
