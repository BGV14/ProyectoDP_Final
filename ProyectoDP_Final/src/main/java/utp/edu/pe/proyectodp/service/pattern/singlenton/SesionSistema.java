package utp.edu.pe.proyectodp.service.pattern.singlenton;

import lombok.Data;

@Data
public class SesionSistema {

    private static SesionSistema instancia;

    private String usuario;
    private String rol;
    private boolean autenticado;

    private SesionSistema(){
    }

    public static synchronized SesionSistema getInstancia(){

        if (instancia == null) {
            instancia = new SesionSistema();
        }

        return instancia;
    }
}
