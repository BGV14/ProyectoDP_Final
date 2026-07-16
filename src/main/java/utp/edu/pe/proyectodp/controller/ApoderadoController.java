package utp.edu.pe.proyectodp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Apoderado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.ApoderadoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Apoderado} que ya existian en
 * {@link ApoderadoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/apoderados")
@RequiredArgsConstructor
public class ApoderadoController {

    private final ApoderadoService service;

    @GetMapping
    public List<Apoderado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apoderado> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Apoderado no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Apoderado> registrar(@Valid @RequestBody Apoderado recurso) {
        Apoderado guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apoderado> actualizar(@PathVariable Long id, @Valid @RequestBody Apoderado recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
