// dto/BoletaDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record BoletaDTO(
        Long id,
        @NotBlank(message = "El número de boleta es obligatorio") String numeroBoleta,
        String fechaEmision,
        double total
) {}