// dto/CursoDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        Long id,
        @NotBlank(message = "El código del curso es obligatorio") String codigoCurso,
        String nombre,
        int horas,
        String grado,
        String nivel,
        String area
) {}