package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Nota;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.NotaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Nota} que ya existian en
 * {@link NotaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService service;

    @GetMapping
    public List<Nota> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Nota no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Nota> registrar(@RequestBody Nota recurso) {
        Nota guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizar(@PathVariable Long id, @RequestBody Nota recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
