package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.CalendarioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.CalendarioEscolarService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link CalendarioEscolar} que ya existian en
 * {@link CalendarioEscolarService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/calendarios-escolares")
@RequiredArgsConstructor
public class CalendarioEscolarController {

    private final CalendarioEscolarService service;

    @GetMapping
    public List<CalendarioEscolar> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarioEscolar> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "CalendarioEscolar no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<CalendarioEscolar> registrar(@RequestBody CalendarioEscolar recurso) {
        CalendarioEscolar guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarioEscolar> actualizar(@PathVariable Long id, @RequestBody CalendarioEscolar recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
