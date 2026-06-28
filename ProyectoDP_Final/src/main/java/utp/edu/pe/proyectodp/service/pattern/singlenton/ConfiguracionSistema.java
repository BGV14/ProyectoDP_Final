package utp.edu.pe.proyectodp.service.pattern.singlenton;

import lombok.Data;

@Data
public class ConfiguracionSistema {

    private static ConfiguracionSistema instancia;

    private String nombreInstitucion;
    private String version;
    private boolean mantenimiento;

    private ConfiguracionSistema(){
        nombreInstitucion = "Sistema Escolar";
        version = "1.0";
        mantenimiento = false;
    }

    public static synchronized ConfiguracionSistema getInstancia(){

        if (instancia == null){
            instancia = new ConfiguracionSistema();
        }

        return instancia;
    }
}