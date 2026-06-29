package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asistencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroBoleta;

    private String fechaEmision;
    private double total;
}