package utp.edu.pe.proyectodp.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int idPersona;

    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;


}
