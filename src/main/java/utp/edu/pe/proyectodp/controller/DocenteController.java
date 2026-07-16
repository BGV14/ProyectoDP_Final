package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.DocenteDTO;
import utp.edu.pe.proyectodp.dto.mapper.DocenteMapper;
import utp.edu.pe.proyectodp.entity.Docente;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DocenteService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Docente} que ya existian en
 * {@link DocenteService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Docentes", description = "Gestión de docentes")
@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService service;
    private final DocenteMapper mapper;

    @GetMapping
    public List<DocenteDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Docente no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> registrar(@Valid @RequestBody DocenteDTO recurso) {
        Docente entidad = mapper.dtoToEntity(recurso);
        Docente guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DocenteDTO recurso) {
        Docente entidad = mapper.dtoToEntity(recurso);
        Docente actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
