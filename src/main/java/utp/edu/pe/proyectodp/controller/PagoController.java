package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.PagoDTO;
import utp.edu.pe.proyectodp.dto.mapper.PagoMapper;
import utp.edu.pe.proyectodp.entity.Pago;
import utp.edu.pe.proyectodp.service.PagoService;

import java.util.List;

@Slf4j
@Tag(name = "Pagos", description = "Gestión de pagos y procesamiento con adapters")
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;
    private final PagoMapper mapper;

    @GetMapping
    public List<PagoDTO> listar() {
        return mapper.entityToDto(pagoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> buscarPorId(@PathVariable Long id) {
        return pagoService.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody PagoDTO pagoDTO) {
        try {
            Pago entidad = mapper.dtoToEntity(pagoDTO);
            Pago guardado = pagoService.guardar(entidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
        } catch (IllegalStateException e) {
            log.warn("Pago rechazado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al registrar el pago", e);
            return ResponseEntity.internalServerError().body("Error al procesar el pago");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO pagoDTO) {
        try {
            Pago entidad = mapper.dtoToEntity(pagoDTO);
            Pago actualizado = pagoService.actualizar(id, entidad);
            return ResponseEntity.ok(mapper.entityToDto(actualizado));
        } catch (Exception e) {
            log.error("Error al actualizar el pago con id {}", id, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}