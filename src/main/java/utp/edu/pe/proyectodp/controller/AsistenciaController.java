package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Asistencia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AsistenciaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Asistencia} que ya existian en
 * {@link AsistenciaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    @GetMapping
    public List<Asistencia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Asistencia no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Asistencia> registrar(@RequestBody Asistencia recurso) {
        Asistencia guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable Long id, @RequestBody Asistencia recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
