package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;
    private double promedio;
}
