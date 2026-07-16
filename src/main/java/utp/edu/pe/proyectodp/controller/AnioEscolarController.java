package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.AnioEscolarDTO;
import utp.edu.pe.proyectodp.dto.mapper.AnioEscolarMapper;
import utp.edu.pe.proyectodp.entity.AnioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AnioEscolarService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link AnioEscolar} que ya existian en
 * {@link AnioEscolarService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Año Escolar", description = "Gestión de años escolares")
@RestController
@RequestMapping("/api/anios-escolares")
@RequiredArgsConstructor
public class AnioEscolarController {

    private final AnioEscolarService service;
    private final AnioEscolarMapper mapper;

    @GetMapping
    public List<AnioEscolarDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnioEscolarDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "AnioEscolar no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<AnioEscolarDTO> registrar(@Valid @RequestBody AnioEscolarDTO recurso) {
        AnioEscolar entidad = mapper.dtoToEntity(recurso);
        AnioEscolar guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnioEscolarDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AnioEscolarDTO recurso) {
        AnioEscolar entidad = mapper.dtoToEntity(recurso);
        AnioEscolar actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
