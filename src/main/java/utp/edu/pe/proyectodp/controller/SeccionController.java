package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Seccion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.SeccionService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Seccion} que ya existian en
 * {@link SeccionService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/secciones")
@RequiredArgsConstructor
public class SeccionController {

    private final SeccionService service;

    @GetMapping
    public List<Seccion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seccion> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Seccion no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Seccion> registrar(@RequestBody Seccion recurso) {
        Seccion guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seccion> actualizar(@PathVariable Long id, @RequestBody Seccion recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
