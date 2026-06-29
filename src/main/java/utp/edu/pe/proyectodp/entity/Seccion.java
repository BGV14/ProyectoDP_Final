package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoSeccion;

    private String nombreSeccion;
    private int capacidad;
}