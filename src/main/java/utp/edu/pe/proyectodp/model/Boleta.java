package utp.edu.pe.proyectodp.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Boleta {

    private String numeroBoleta;
    private String fechaEmision;
    private double total;
}
