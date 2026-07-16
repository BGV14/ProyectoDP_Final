package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Director;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DirectorService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Director} que ya existian en
 * {@link DirectorService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService service;

    @GetMapping
    public List<Director> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Director no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Director> registrar(@RequestBody Director recurso) {
        Director guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> actualizar(@PathVariable Long id, @RequestBody Director recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
