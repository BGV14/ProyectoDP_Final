package utp.edu.pe.proyectodp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "apoderado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Apoderado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoApoderado;

    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;
}
