// entity/Estudiante.java
package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El código del estudiante debe ser un número positivo")
    @Column(unique = true)
    private int codigoEstudiante;

    private String estado;

}
