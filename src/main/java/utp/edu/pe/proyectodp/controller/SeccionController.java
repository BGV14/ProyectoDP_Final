package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.SeccionDTO;
import utp.edu.pe.proyectodp.dto.mapper.SeccionMapper;
import utp.edu.pe.proyectodp.entity.Seccion;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.SeccionService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Seccion} que ya existian en
 * {@link SeccionService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Secciones", description = "Gestión de secciones de aula")
@RestController
@RequestMapping("/api/secciones")
@RequiredArgsConstructor
public class SeccionController {

    private final SeccionService service;
    private final SeccionMapper mapper;

    @GetMapping
    public List<SeccionDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeccionDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Seccion no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<SeccionDTO> registrar(@Valid @RequestBody SeccionDTO recurso) {
        Seccion entidad = mapper.dtoToEntity(recurso);
        Seccion guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeccionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SeccionDTO recurso) {
        Seccion entidad = mapper.dtoToEntity(recurso);
        Seccion actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
