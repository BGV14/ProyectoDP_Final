package utp.edu.pe.proyectodp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Distrito;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DistritoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Distrito} que ya existian en
 * {@link DistritoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/distritos")
@RequiredArgsConstructor
public class DistritoController {

    private final DistritoService service;

    @GetMapping
    public List<Distrito> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Distrito> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Distrito no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Distrito> registrar(@Valid @RequestBody Distrito recurso) {
        Distrito guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Distrito> actualizar(@PathVariable Long id, @Valid @RequestBody Distrito recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
