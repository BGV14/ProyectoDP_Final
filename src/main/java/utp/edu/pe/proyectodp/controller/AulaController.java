package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.AulaDTO;
import utp.edu.pe.proyectodp.dto.mapper.AulaMapper;
import utp.edu.pe.proyectodp.entity.Aula;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AulaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Aula} que ya existian en
 * {@link AulaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Aulas", description = "Gestión de aulas")
@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService service;
    private final AulaMapper mapper;

    @GetMapping
    public List<AulaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Aula no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<AulaDTO> registrar(@Valid @RequestBody AulaDTO recurso) {
        Aula entidad = mapper.dtoToEntity(recurso);
        Aula guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AulaDTO recurso) {
        Aula entidad = mapper.dtoToEntity(recurso);
        Aula actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
