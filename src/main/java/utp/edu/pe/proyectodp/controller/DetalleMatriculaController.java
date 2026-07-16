package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.DetalleMatriculaDTO;
import utp.edu.pe.proyectodp.dto.mapper.DetalleMatriculaMapper;
import utp.edu.pe.proyectodp.entity.DetalleMatricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DetalleMatriculaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link DetalleMatricula} que ya existian en
 * {@link DetalleMatriculaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Detalle de Matrícula", description = "Gestión de detalles de matrícula")
@RestController
@RequestMapping("/api/detalle-matriculas")
@RequiredArgsConstructor
public class DetalleMatriculaController {

    private final DetalleMatriculaService service;
    private final DetalleMatriculaMapper mapper;

    @GetMapping
    public List<DetalleMatriculaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleMatriculaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "DetalleMatricula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<DetalleMatriculaDTO> registrar(@Valid @RequestBody DetalleMatriculaDTO recurso) {
        DetalleMatricula entidad = mapper.dtoToEntity(recurso);
        DetalleMatricula guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleMatriculaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleMatriculaDTO recurso) {
        DetalleMatricula entidad = mapper.dtoToEntity(recurso);
        DetalleMatricula actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
