package utp.edu.pe.proyectodp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoAdministrador;

    @Column(name = "usuario")
    private String user;

    private String password;
}