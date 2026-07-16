// dto/MatriculaDTO.java
package utp.edu.pe.proyectodp.dto;

public record MatriculaDTO(
        Long id,
        int codigoMatricula,
        String fechaMatricula,
        String turno,
        String tipoMatricula
) {}