package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalleMatricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoDeMatricula;

    private String fechaRegistro;
}
