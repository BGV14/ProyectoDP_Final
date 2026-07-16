package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.MatriculaDTO;
import utp.edu.pe.proyectodp.dto.mapper.MatriculaMapper;
import utp.edu.pe.proyectodp.entity.Matricula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.MatriculaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Matricula} que ya existian en
 * {@link MatriculaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Matrículas", description = "Gestión de matrículas de estudiantes")
@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;
    private final MatriculaMapper mapper;

    @GetMapping
    public List<MatriculaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Matricula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> registrar(@Valid @RequestBody MatriculaDTO recurso) {
        Matricula entidad = mapper.dtoToEntity(recurso);
        Matricula guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MatriculaDTO recurso) {
        Matricula entidad = mapper.dtoToEntity(recurso);
        Matricula actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
