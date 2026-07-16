// dto/NotaDTO.java
package utp.edu.pe.proyectodp.dto;

public record NotaDTO(
        Long id,
        double nota1,
        double nota2,
        double nota3,
        double nota4,
        double promedio
) {}