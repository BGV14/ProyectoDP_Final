package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.LibretaDTO;
import utp.edu.pe.proyectodp.dto.mapper.LibretaMapper;
import utp.edu.pe.proyectodp.entity.Libreta;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.LibretaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Libreta} que ya existian en
 * {@link LibretaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Libretas", description = "Gestión de libretas de calificaciones")
@RestController
@RequestMapping("/api/libretas")
@RequiredArgsConstructor
public class LibretaController {

    private final LibretaService service;
    private final LibretaMapper mapper;

    @GetMapping
    public List<LibretaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibretaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Libreta no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<LibretaDTO> registrar(@Valid @RequestBody LibretaDTO recurso) {
        Libreta entidad = mapper.dtoToEntity(recurso);
        Libreta guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibretaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody LibretaDTO recurso) {
        Libreta entidad = mapper.dtoToEntity(recurso);
        Libreta actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
