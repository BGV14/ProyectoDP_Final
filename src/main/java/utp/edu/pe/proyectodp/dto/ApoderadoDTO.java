// dto/ApoderadoDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record ApoderadoDTO(
        Long id,
        @NotBlank(message = "El código del apoderado es obligatorio") String codigoApoderado,
        String nombre,
        String apellido,
        String dni,
        String fechaNacimiento,
        String direccion,
        int telefono
) {}