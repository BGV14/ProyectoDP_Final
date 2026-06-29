package utp.edu.pe.proyectodp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnioEscolar {

    private String codigoAnio;
    private int anio;
    private String fechaInicio;
    private String fechaFin;
}
