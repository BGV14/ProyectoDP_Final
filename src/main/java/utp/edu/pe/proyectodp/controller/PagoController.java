package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.service.PagoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public List<Pago> listar(){
        return pagoService.listar();
    }

    @GetMapping("/{id")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@RequestBody Pago pago) {
        try {
            Pago guardado = pagoService.guardar(pago);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalStateException e) {
            log.warn("Pago rechazado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al registrar el pago", e);
            return ResponseEntity.internalServerError().body("Error al procesar el pago");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        try {
            return ResponseEntity.ok(pagoService.actualizar(id, pago));
        } catch (Exception e) {
            log.error("Error al actualizar el pago con id {}", id, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
