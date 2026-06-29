package utp.edu.pe.proyectodp.service.pattern.singlenton;

import lombok.Data;

@Data
public class AnioEscolarSingleton {

    private static AnioEscolarSingleton instancia;

    private int anio;
    private String fechaInicio;
    private String fechaFin;
    private boolean activo;

    private AnioEscolarSingleton() {
        activo = true;
    }

    public static synchronized AnioEscolarSingleton getInstancia() {

        if (instancia == null) {
            instancia = new AnioEscolarSingleton();
        }

        return instancia;
    }
}
