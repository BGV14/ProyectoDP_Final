package utp.edu.pe.proyectodp.dto;

import jakarta.validation.constraints.NotBlank;

public record AdministradorDTO(
        Long id,
        @NotBlank(message = "El código del administrador es obligatorio") String codigoAdministrador,
        @NotBlank(message = "El usuario es obligatorio") String user,
        String password
) {
}
