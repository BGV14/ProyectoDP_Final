// dto/EstudianteDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.Positive;

public record EstudianteDTO(
        Long id,
        @Positive(message = "El código del estudiante debe ser un número positivo") int codigoEstudiante,
        String estado
) {}