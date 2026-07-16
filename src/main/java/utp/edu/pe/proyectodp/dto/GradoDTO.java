// dto/GradoDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record GradoDTO(
        Long id,
        @NotBlank(message = "El código del grado es obligatorio") String codigoGrado,
        String nombreGrado,
        String nivel
) {}