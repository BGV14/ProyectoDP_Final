package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.GradoDTO;
import utp.edu.pe.proyectodp.dto.mapper.GradoMapper;
import utp.edu.pe.proyectodp.entity.Grado;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.GradoService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Grado} que ya existian en
 * {@link GradoService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Grados", description = "Gestión de grados escolares")
@RestController
@RequestMapping("/api/grados")
@RequiredArgsConstructor
public class GradoController {

    private final GradoService service;
    private final GradoMapper mapper;

    @GetMapping
    public List<GradoDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Grado no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<GradoDTO> registrar(@Valid @RequestBody GradoDTO recurso) {
        Grado entidad = mapper.dtoToEntity(recurso);
        Grado guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody GradoDTO recurso) {
        Grado entidad = mapper.dtoToEntity(recurso);
        Grado actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
