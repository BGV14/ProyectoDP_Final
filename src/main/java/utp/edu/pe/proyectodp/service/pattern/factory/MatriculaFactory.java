package utp.edu.pe.proyectodp.service.pattern.factory;

public class MatriculaFactory {

    public static Matricula crearMatricula(String tipo) {
        if (tipo.equalsIgnoreCase("REGULAR")) {
            return new MatriculaRegular();
        } else if (tipo.equalsIgnoreCase("TRASLADO")) {
            return new MatriculaTraslado();
        } else if (tipo.equalsIgnoreCase("EXTEMPORANEA")) {
            return new MatriculaExtemporanea();
        }
        throw new IllegalArgumentException("Tipo de matrícula no válido: " + tipo);
    }
}
