// dto/SeccionDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record SeccionDTO(
        Long id,
        @NotBlank(message = "El código de sección es obligatorio") String codigoSeccion,
        String nombreSeccion,
        int capacidad
) {}