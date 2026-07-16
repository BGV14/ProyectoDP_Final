// dto/DocenteDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DocenteDTO(
        Long id,
        @Positive(message = "El código del docente debe ser un número positivo") int codigoDocente,
        @NotBlank(message = "El nombre es obligatorio") String nombres,
        @NotBlank(message = "El apellido es obligatorio") String apellidos,
        String especialidad,
        @Email(message = "El correo no tiene un formato válido") String correo
) {}