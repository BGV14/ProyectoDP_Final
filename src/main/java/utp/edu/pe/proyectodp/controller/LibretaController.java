package utp.edu.pe.proyectodp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Libreta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.LibretaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Libreta} que ya existian en
 * {@link LibretaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/libretas")
@RequiredArgsConstructor
public class LibretaController {

    private final LibretaService service;

    @GetMapping
    public List<Libreta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libreta> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Libreta no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Libreta> registrar(@Valid @RequestBody Libreta recurso) {
        Libreta guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libreta> actualizar(@PathVariable Long id, @Valid @RequestBody Libreta recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
