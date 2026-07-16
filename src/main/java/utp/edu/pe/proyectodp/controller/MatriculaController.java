package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Matricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.MatriculaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Matricula} que ya existian en
 * {@link MatriculaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Matrículas", description = "Gestión de matrículas de estudiantes")
@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;

    @GetMapping
    public List<Matricula> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Matricula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Matricula> registrar(@Valid @RequestBody Matricula recurso) {
        Matricula guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(@PathVariable Long id, @Valid @RequestBody Matricula recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
