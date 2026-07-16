// dto/PagoDTO.java
package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PagoDTO(
        Long id,
        String codigoPago,
        @Positive(message = "El monto debe ser mayor a cero") double monto,
        String fechaPago,
        String estadoPago,
        @NotBlank(message = "El método de pago es obligatorio") String metodoPago
) {}