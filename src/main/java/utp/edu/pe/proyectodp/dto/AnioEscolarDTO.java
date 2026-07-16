// dto/AnioEscolarDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record AnioEscolarDTO(
        Long id,
        @NotBlank(message = "El código de año escolar es obligatorio") String codigoAnio,
        int anio,
        String fechaInicio,
        String fechaFin
) {}