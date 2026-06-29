package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "calendarioEscolar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioEscolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEvento;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoEvento;
    private String estado;
}