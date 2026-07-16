package utp.edu.pe.proyectodp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GloblalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpoError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarArgumentoInvalido(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Datos inválidos");
        log.warn("Error de validación: {}", mensaje);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoError(HttpStatus.BAD_REQUEST, mensaje));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErrorGeneral(Exception ex) {
        log.error("Error inesperado no controlado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(cuerpoError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado. Intente más tarde."));
    }

    private Map<String, Object> cuerpoError(HttpStatus status, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("status", status.value());
        cuerpo.put("error", status.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return cuerpo;
    }

}
