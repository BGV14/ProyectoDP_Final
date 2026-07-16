package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.EstudianteDTO;
import utp.edu.pe.proyectodp.dto.mapper.EstudianteMapper;
import utp.edu.pe.proyectodp.entity.Estudiante;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.EstudianteService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Estudiante} que ya existian en
 * {@link EstudianteService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Estudiantes", description = "Gestión de estudiantes")
@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService service;
    private final EstudianteMapper mapper;

    @GetMapping
    public List<EstudianteDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Estudiante no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> registrar(@Valid @RequestBody EstudianteDTO recurso) {
        Estudiante entidad = mapper.dtoToEntity(recurso);
        Estudiante guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteDTO recurso) {
        Estudiante entidad = mapper.dtoToEntity(recurso);
        Estudiante actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
