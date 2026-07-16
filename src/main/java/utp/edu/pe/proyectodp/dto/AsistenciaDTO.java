// dto/AsistenciaDTO.java
package utp.edu.pe.proyectodp.dto;

public record AsistenciaDTO(
        Long id,
        double porcentaje,
        int faltas,
        int tardanzas
) {}