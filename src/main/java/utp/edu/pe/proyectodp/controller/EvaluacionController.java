package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.EvaluacionDTO;
import utp.edu.pe.proyectodp.dto.mapper.EvaluacionMapper;
import utp.edu.pe.proyectodp.entity.Evaluacion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.EvaluacionService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Evaluacion} que ya existian en
 * {@link EvaluacionService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Evaluaciones", description = "Gestión de evaluaciones académicas")
@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService service;
    private final EvaluacionMapper mapper;

    @GetMapping
    public List<EvaluacionDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Evaluacion no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<EvaluacionDTO> registrar(@Valid @RequestBody EvaluacionDTO recurso) {
        Evaluacion entidad = mapper.dtoToEntity(recurso);
        Evaluacion guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionDTO recurso) {
        Evaluacion entidad = mapper.dtoToEntity(recurso);
        Evaluacion actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
