package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.DirectorDTO;
import utp.edu.pe.proyectodp.dto.mapper.DirectorMapper;
import utp.edu.pe.proyectodp.entity.Director;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.DirectorService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Director} que ya existian en
 * {@link DirectorService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Directores", description = "Gestión de directores")
@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService service;
    private final DirectorMapper mapper;

    @GetMapping
    public List<DirectorDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Director no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> registrar(@Valid @RequestBody DirectorDTO recurso) {
        Director entidad = mapper.dtoToEntity(recurso);
        Director guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DirectorDTO recurso) {
        Director entidad = mapper.dtoToEntity(recurso);
        Director actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
