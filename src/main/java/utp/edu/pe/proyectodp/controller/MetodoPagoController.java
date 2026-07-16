package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.MetodoPago;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.MetodoPagoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link MetodoPago} que ya existian en
 * {@link MetodoPagoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService service;

    @GetMapping
    public List<MetodoPago> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "MetodoPago no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<MetodoPago> registrar(@RequestBody MetodoPago recurso) {
        MetodoPago guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizar(@PathVariable Long id, @RequestBody MetodoPago recurso) {
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
