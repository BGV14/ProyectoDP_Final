package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metodoPago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoPago;
    private String descripcion;
}
