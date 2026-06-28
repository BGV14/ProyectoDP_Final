package utp.edu.pe.proyectodp.service.pattern.singlenton;

import lombok.Data;

@Data
public class GeneradorCodigo {

    private static GeneradorCodigo instancia;

    private int contadorMatricula = 1;
    private int contadorPago = 1;
    private int contadorCurso = 1;
    private int contadorDocente = 1;
    private int contadorEstudiante = 1;
    private int contadorBoleta = 1;

    private GeneradorCodigo(){
    }

    public static synchronized GeneradorCodigo getInstancia(){
        if (instancia == null) {
            instancia = new GeneradorCodigo();
        }

        return instancia;
    }

    public String generarCodigoMatricula() {
        return String.format("MAT-%04d", contadorMatricula++);
    }

    public String generarCodigoPago() {
        return String.format("PAG-%04d", contadorPago++);
    }

    public String generarCodigoCurso() {
        return String.format("CUR-%04d", contadorCurso++);
    }

    public String generarCodigoDocente() {
        return String.format("DOC-%04d", contadorDocente++);
    }

    public String generarCodigoEstudiante() {
        return String.format("EST-%04d", contadorEstudiante++);
    }

    public String generarCodigoBoleta() {
        return String.format("BOL-%04d", contadorBoleta++);
    }
}