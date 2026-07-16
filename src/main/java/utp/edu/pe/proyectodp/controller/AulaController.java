package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Aula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AulaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Aula} que ya existian en
 * {@link AulaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService service;

    @GetMapping
    public List<Aula> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Aula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Aula> registrar(@RequestBody Aula recurso) {
        Aula guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> actualizar(@PathVariable Long id, @RequestBody Aula recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
