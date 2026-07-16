package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.MetodoPagoDTO;
import utp.edu.pe.proyectodp.dto.mapper.MetodoPagoMapper;
import utp.edu.pe.proyectodp.entity.MetodoPago;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.MetodoPagoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link MetodoPago} que ya existian en
 * {@link MetodoPagoService} pero que no estaban disponibles vía REST.
 *
 * Homologado con {@link AdministradorController}: usa {@link MetodoPagoMapper}
 * para no exponer la entidad JPA directamente en la capa REST.
 */
@Slf4j
@Tag(name = "Métodos de Pago", description = "Gestión de métodos de pago")
@RestController
@RequestMapping("/api/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService service;
    private final MetodoPagoMapper mapper;

    @GetMapping
    public List<MetodoPagoDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "MetodoPago no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> registrar(@Valid @RequestBody MetodoPagoDTO recurso) {
        MetodoPago entidad = mapper.dtoToEntity(recurso);
        MetodoPago guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MetodoPagoDTO recurso) {
        MetodoPago entidad = mapper.dtoToEntity(recurso);
        MetodoPago actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}