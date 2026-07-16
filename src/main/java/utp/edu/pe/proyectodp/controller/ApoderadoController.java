package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.ApoderadoDTO;
import utp.edu.pe.proyectodp.dto.mapper.ApoderadoMapper;
import utp.edu.pe.proyectodp.entity.Apoderado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.ApoderadoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Apoderado} que ya existian en
 * {@link ApoderadoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Apoderados", description = "Gestión de apoderados de los estudiantes")
@RestController
@RequestMapping("/api/apoderados")
@RequiredArgsConstructor
public class ApoderadoController {

    private final ApoderadoService service;
    private final ApoderadoMapper mapper;

    @GetMapping
    public List<ApoderadoDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Apoderado no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<ApoderadoDTO> registrar(@Valid @RequestBody ApoderadoDTO recurso) {
        Apoderado entidad = mapper.dtoToEntity(recurso);
        Apoderado guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ApoderadoDTO recurso) {
        Apoderado entidad = mapper.dtoToEntity(recurso);
        Apoderado actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
