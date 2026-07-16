package utp.edu.pe.proyectodp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Provincia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.ProvinciaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Provincia} que ya existian en
 * {@link ProvinciaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/provincias")
@RequiredArgsConstructor
public class ProvinciaController {

    private final ProvinciaService service;

    @GetMapping
    public List<Provincia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provincia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Provincia no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Provincia> registrar(@Valid @RequestBody Provincia recurso) {
        Provincia guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provincia> actualizar(@PathVariable Long id, @Valid @RequestBody Provincia recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
