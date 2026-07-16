package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.CalendarioEscolarDTO;
import utp.edu.pe.proyectodp.dto.mapper.CalendarioEscolarMapper;
import utp.edu.pe.proyectodp.entity.CalendarioEscolar;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.CalendarioEscolarService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link CalendarioEscolar} que ya existian en
 * {@link CalendarioEscolarService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Calendario Escolar", description = "Gestión del calendario escolar")
@RestController
@RequestMapping("/api/calendarios-escolares")
@RequiredArgsConstructor
public class CalendarioEscolarController {

    private final CalendarioEscolarService service;
    private final CalendarioEscolarMapper mapper;

    @GetMapping
    public List<CalendarioEscolarDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarioEscolarDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "CalendarioEscolar no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<CalendarioEscolarDTO> registrar(@Valid @RequestBody CalendarioEscolarDTO recurso) {
        CalendarioEscolar entidad = mapper.dtoToEntity(recurso);
        CalendarioEscolar guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarioEscolarDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CalendarioEscolarDTO recurso) {
        CalendarioEscolar entidad = mapper.dtoToEntity(recurso);
        CalendarioEscolar actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
