// entity/Administrador.java
package utp.edu.pe.proyectodp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El código del administrador es obligatorio")
    @Column(unique = true)
    private String codigoAdministrador;

    @NotBlank(message = "El usuario es obligatorio")
    @Column(name = "usuario")
    private String user;

    // WRITE_ONLY: se puede enviar en el request (POST/PUT) pero nunca se
    // devuelve en las respuestas JSON. Antes, GET /api/administradores
    // exponía el password de cada administrador.
    @NotBlank(message = "El password es obligatorio")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}