package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.NotaDTO;
import utp.edu.pe.proyectodp.dto.mapper.NotaMapper;
import utp.edu.pe.proyectodp.entity.Nota;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.NotaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Nota} que ya existian en
 * {@link NotaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Notas", description = "Gestión de notas de evaluaciones")
@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService service;
    private final NotaMapper mapper;

    @GetMapping
    public List<NotaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Nota no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<NotaDTO> registrar(@Valid @RequestBody NotaDTO recurso) {
        Nota entidad = mapper.dtoToEntity(recurso);
        Nota guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotaDTO recurso) {
        Nota entidad = mapper.dtoToEntity(recurso);
        Nota actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
