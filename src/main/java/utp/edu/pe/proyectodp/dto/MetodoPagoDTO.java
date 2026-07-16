package utp.edu.pe.proyectodp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MetodoPagoDTO(
        Long id,
        @NotBlank(message = "El tipo de pago es obligatorio") String tipoPago,
        String descripcion
) {
}