// entity/Docente.java
package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "El código del docente debe ser un número positivo")
    @Column(unique = true)
    private int codigoDocente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    private String especialidad;

    @Email(message = "El correo no tiene un formato válido")
    private String correo;

}
