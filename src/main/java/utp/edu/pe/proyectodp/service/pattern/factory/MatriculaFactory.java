package utp.edu.pe.proyectodp.service.pattern.factory;

import utp.edu.pe.proyectodp.service.pattern.factory.interfaces.ProcesadorMatricula;

public class MatriculaFactory {

    private MatriculaFactory(){

    }

    public static ProcesadorMatricula crearMatricula(String tipo) {
        if (tipo.equalsIgnoreCase("REGULAR")) {
            return new ProcesadorMatriculaRegular();
        } else if (tipo.equalsIgnoreCase("TRASLADO")) {
            return new ProcesadorMatriculaTraslado();
        } else if (tipo.equalsIgnoreCase("EXTEMPORANEA")) {
            return new ProcesadorMatriculaExtemporanea();
        }
        throw new IllegalArgumentException("Tipo de matrícula no válido: " + tipo);
    }
}