// dto/LibretaDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record LibretaDTO(
        Long id,
        @NotBlank(message = "El código de libreta es obligatorio") String codigoLibreta,
        String fechaEmision,
        String observacion,
        double promedioFinal,
        String estado
) {}