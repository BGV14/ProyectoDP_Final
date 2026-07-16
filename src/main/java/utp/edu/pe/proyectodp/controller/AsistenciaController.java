package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.AsistenciaDTO;
import utp.edu.pe.proyectodp.dto.mapper.AsistenciaMapper;
import utp.edu.pe.proyectodp.entity.Asistencia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AsistenciaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Asistencia} que ya existian en
 * {@link AsistenciaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Asistencias", description = "Registro de asistencia de estudiantes")
@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;
    private final AsistenciaMapper mapper;

    @GetMapping
    public List<AsistenciaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Asistencia no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaDTO> registrar(@Valid @RequestBody AsistenciaDTO recurso) {
        Asistencia entidad = mapper.dtoToEntity(recurso);
        Asistencia guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AsistenciaDTO recurso) {
        Asistencia entidad = mapper.dtoToEntity(recurso);
        Asistencia actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
