package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AnioEscolar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnioEscolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoAnio;

    private int anio;
    private String fechaInicio;
    private String fechaFin;
}
