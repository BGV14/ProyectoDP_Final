package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.BoletaDTO;
import utp.edu.pe.proyectodp.dto.mapper.BoletaMapper;
import utp.edu.pe.proyectodp.entity.Boleta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.BoletaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Boleta} que ya existian en
 * {@link BoletaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Boletas", description = "Gestión de boletas de notas")
@RestController
@RequestMapping("/api/boletas")
@RequiredArgsConstructor
public class BoletaController {

    private final BoletaService service;
    private final BoletaMapper mapper;

    @GetMapping
    public List<BoletaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Boleta no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<BoletaDTO> registrar(@Valid @RequestBody BoletaDTO recurso) {
        Boleta entidad = mapper.dtoToEntity(recurso);
        Boleta guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody BoletaDTO recurso) {
        Boleta entidad = mapper.dtoToEntity(recurso);
        Boleta actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
