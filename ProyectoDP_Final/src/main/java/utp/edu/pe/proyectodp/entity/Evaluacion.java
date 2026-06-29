package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoEvaluacion;
    private String fechaEvaluacion;
}
