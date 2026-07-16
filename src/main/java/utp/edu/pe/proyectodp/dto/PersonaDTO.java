// dto/PersonaDTO.java
package utp.edu.pe.proyectodp.dto;

public record PersonaDTO(
        Long id,
        int idPersona,
        String nombre,
        String apellido,
        String dni,
        String fechaNacimiento,
        String direccion,
        int telefono
) {}