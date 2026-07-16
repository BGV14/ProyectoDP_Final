package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.DetalleMatricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DetalleMatriculaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link DetalleMatricula} que ya existian en
 * {@link DetalleMatriculaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Detalle de Matrícula", description = "Gestión de detalles de matrícula")
@RestController
@RequestMapping("/api/detalle-matriculas")
@RequiredArgsConstructor
public class DetalleMatriculaController {

    private final DetalleMatriculaService service;

    @GetMapping
    public List<DetalleMatricula> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleMatricula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "DetalleMatricula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<DetalleMatricula> registrar(@Valid @RequestBody DetalleMatricula recurso) {
        DetalleMatricula guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleMatricula> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleMatricula recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
