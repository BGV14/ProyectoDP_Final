package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.PersonaDTO;
import utp.edu.pe.proyectodp.dto.mapper.PersonaMapper;
import utp.edu.pe.proyectodp.entity.Persona;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.PersonaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Persona} que ya existian en
 * {@link PersonaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Personas", description = "Gestión de personas del sistema")
@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService service;
    private final PersonaMapper mapper;

    @GetMapping
    public List<PersonaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Persona no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@Valid @RequestBody PersonaDTO recurso) {
        Persona entidad = mapper.dtoToEntity(recurso);
        Persona guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PersonaDTO recurso) {
        Persona entidad = mapper.dtoToEntity(recurso);
        Persona actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
