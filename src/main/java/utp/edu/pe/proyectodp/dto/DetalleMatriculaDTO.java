// dto/DetalleMatriculaDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record DetalleMatriculaDTO(
        Long id,
        @NotBlank(message = "El código de matrícula es obligatorio") String codigoDeMatricula,
        String fechaRegistro
) {}