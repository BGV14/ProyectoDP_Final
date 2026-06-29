package utp.edu.pe.proyectodp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Libreta {

    private String codigoLibreta;
    private String fechaEmision;
    private String observacion;
    private double promedioFinal;
    private String estado;

}
