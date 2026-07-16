package utp.edu.pe.proyectodp.service.pattern.factory;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.factory.interfaces.ProcesadorMatricula;

import java.util.List;
import java.util.Map;

/**
 * Factory Method resuelto contra el registro de beans de Spring.
 *
 * <p>Cada implementación de {@link ProcesadorMatricula} se registra con
 * @Component("TIPO"). Agregar un tipo de matrícula nuevo solo requiere crear
 * la clase e implementar la interfaz; esta fábrica no se modifica (Open/Closed).</p>
 */
@Component
public class MatriculaFactory {

    private final List<ProcesadorMatricula> procesadoresDisponibles;
    private Map<String, ProcesadorMatricula> procesadores;

    public MatriculaFactory(List<ProcesadorMatricula> procesadoresDisponibles) {
        this.procesadoresDisponibles = procesadoresDisponibles;
    }

    @jakarta.annotation.PostConstruct
    void indexar() {
        procesadores = procesadoresDisponibles.stream()
                .collect(java.util.stream.Collectors.toMap(ProcesadorMatricula::getTipo, p -> p));
    }

    public ProcesadorMatricula crearMatricula(String tipo) {
        ProcesadorMatricula procesador = tipo == null ? null : procesadores.get(tipo.toUpperCase());
        if (procesador == null) {
            throw new IllegalArgumentException("Tipo de matrícula no válido: " + tipo);
        }
        return procesador;
    }
}