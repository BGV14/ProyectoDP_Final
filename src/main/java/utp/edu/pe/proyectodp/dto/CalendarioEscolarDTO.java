// dto/CalendarioEscolarDTO.java
package utp.edu.pe.proyectodp.dto;

import java.time.LocalDate;

public record CalendarioEscolarDTO(
        Long id,
        String nombreEvento,
        String descripcion,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String tipoEvento,
        String estado
) {}