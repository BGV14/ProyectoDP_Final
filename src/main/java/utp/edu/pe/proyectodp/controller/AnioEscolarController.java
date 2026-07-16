package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.AnioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AnioEscolarService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link AnioEscolar} que ya existian en
 * {@link AnioEscolarService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Año Escolar", description = "Gestión de años escolares")
@RestController
@RequestMapping("/api/anios-escolares")
@RequiredArgsConstructor
public class AnioEscolarController {

    private final AnioEscolarService service;

    @GetMapping
    public List<AnioEscolar> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnioEscolar> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "AnioEscolar no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<AnioEscolar> registrar(@Valid @RequestBody AnioEscolar recurso) {
        AnioEscolar guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnioEscolar> actualizar(@PathVariable Long id, @Valid @RequestBody AnioEscolar recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
