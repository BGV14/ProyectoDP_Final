package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libreta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libreta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoLibreta;

    private String fechaEmision;
    private String observacion;
    private double promedioFinal;
    private String estado;

}