// dto/EvaluacionDTO.java
package utp.edu.pe.proyectodp.dto;

public record EvaluacionDTO(
        Long id,
        String tipoEvaluacion,
        String fechaEvaluacion
) {}