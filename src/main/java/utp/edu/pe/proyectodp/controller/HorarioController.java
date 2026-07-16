package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.HorarioDTO;
import utp.edu.pe.proyectodp.dto.mapper.HorarioMapper;
import utp.edu.pe.proyectodp.entity.Horario;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.HorarioService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Horario} que ya existian en
 * {@link HorarioService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Horarios", description = "Gestión de horarios de clase")
@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService service;
    private final HorarioMapper mapper;

    @GetMapping
    public List<HorarioDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Horario no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> registrar(@Valid @RequestBody HorarioDTO recurso) {
        Horario entidad = mapper.dtoToEntity(recurso);
        Horario guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HorarioDTO recurso) {
        Horario entidad = mapper.dtoToEntity(recurso);
        Horario actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
