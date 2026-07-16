// dto/DirectorDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record DirectorDTO(
        Long id,
        @NotBlank(message = "El código del director es obligatorio") String codigoDirector,
        String oficina,
        String correo
) {}