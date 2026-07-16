// dto/AulaDTO.java
package utp.edu.pe.proyectodp.dto;

public record AulaDTO(
        Long id,
        int codigoAula,
        String numero,
        int capacidad,
        String pabellon
) {}