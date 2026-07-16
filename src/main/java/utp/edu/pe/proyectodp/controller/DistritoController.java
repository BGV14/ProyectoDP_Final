package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.DistritoDTO;
import utp.edu.pe.proyectodp.dto.mapper.DistritoMapper;
import utp.edu.pe.proyectodp.entity.Distrito;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DistritoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Distrito} que ya existian en
 * {@link DistritoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Distritos", description = "Gestión de distritos geográficos")
@RestController
@RequestMapping("/api/distritos")
@RequiredArgsConstructor
public class DistritoController {

    private final DistritoService service;
    private final DistritoMapper mapper;

    @GetMapping
    public List<DistritoDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Distrito no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<DistritoDTO> registrar(@Valid @RequestBody DistritoDTO recurso) {
        Distrito entidad = mapper.dtoToEntity(recurso);
        Distrito guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DistritoDTO recurso) {
        Distrito entidad = mapper.dtoToEntity(recurso);
        Distrito actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
