package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.CursoDTO;
import utp.edu.pe.proyectodp.dto.mapper.CursoMapper;
import utp.edu.pe.proyectodp.entity.Curso;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.CursoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Curso} que ya existian en
 * {@link CursoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Cursos", description = "Gestión de cursos")
@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;
    private final CursoMapper mapper;

    @GetMapping
    public List<CursoDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Curso no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> registrar(@Valid @RequestBody CursoDTO recurso) {
        Curso entidad = mapper.dtoToEntity(recurso);
        Curso guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CursoDTO recurso) {
        Curso entidad = mapper.dtoToEntity(recurso);
        Curso actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
