package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Docente;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DocenteService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Docente} que ya existian en
 * {@link DocenteService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService service;

    @GetMapping
    public List<Docente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Docente no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Docente> registrar(@RequestBody Docente recurso) {
        Docente guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizar(@PathVariable Long id, @RequestBody Docente recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
