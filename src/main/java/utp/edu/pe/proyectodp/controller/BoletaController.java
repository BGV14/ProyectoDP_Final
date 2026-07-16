package utp.edu.pe.proyectodp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Boleta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.BoletaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Boleta} que ya existian en
 * {@link BoletaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/boletas")
@RequiredArgsConstructor
public class BoletaController {

    private final BoletaService service;

    @GetMapping
    public List<Boleta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Boleta no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Boleta> registrar(@Valid @RequestBody Boleta recurso) {
        Boleta guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> actualizar(@PathVariable Long id, @Valid @RequestBody Boleta recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
