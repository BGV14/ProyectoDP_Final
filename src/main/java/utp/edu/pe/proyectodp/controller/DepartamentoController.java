package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Departamento;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DepartamentoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Departamento} que ya existian en
 * {@link DepartamentoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Departamentos", description = "Gestión de departamentos geográficos")
@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService service;

    @GetMapping
    public List<Departamento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Departamento no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Departamento> registrar(@Valid @RequestBody Departamento recurso) {
        Departamento guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable Long id, @Valid @RequestBody Departamento recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
