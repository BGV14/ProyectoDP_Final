package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Grado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.GradoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Grado} que ya existian en
 * {@link GradoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/grados")
@RequiredArgsConstructor
public class GradoController {

    private final GradoService service;

    @GetMapping
    public List<Grado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grado> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Grado no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Grado> registrar(@RequestBody Grado recurso) {
        Grado guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grado> actualizar(@PathVariable Long id, @RequestBody Grado recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
