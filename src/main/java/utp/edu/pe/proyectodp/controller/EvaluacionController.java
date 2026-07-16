package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Evaluacion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.EvaluacionService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Evaluacion} que ya existian en
 * {@link EvaluacionService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService service;

    @GetMapping
    public List<Evaluacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Evaluacion no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Evaluacion> registrar(@RequestBody Evaluacion recurso) {
        Evaluacion guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
