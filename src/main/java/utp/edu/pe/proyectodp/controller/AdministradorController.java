package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AdministradorService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Administrador} que ya existian en
 * {@link AdministradorService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService service;

    @GetMapping
    public List<Administrador> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Administrador no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Administrador> registrar(@RequestBody Administrador recurso) {
        Administrador guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(@PathVariable Long id, @RequestBody Administrador recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
