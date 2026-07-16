// dto/HorarioDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record HorarioDTO(
        Long id,
        @NotBlank(message = "El código del horario es obligatorio") String codigoHorario,
        String horaInicio,
        String horaFin,
        String dia
) {}