package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.ProvinciaDTO;
import utp.edu.pe.proyectodp.dto.mapper.ProvinciaMapper;
import utp.edu.pe.proyectodp.entity.Provincia;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.ProvinciaService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Provincia} que ya existian en
 * {@link ProvinciaService} pero que no estaban disponibles vía REST.
 */
@Slf4j
@Tag(name = "Provincias", description = "Gestión de provincias geográficas")
@RestController
@RequestMapping("/api/provincias")
@RequiredArgsConstructor
public class ProvinciaController {

    private final ProvinciaService service;
    private final ProvinciaMapper mapper;

    @GetMapping
    public List<ProvinciaDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Provincia no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<ProvinciaDTO> registrar(@Valid @RequestBody ProvinciaDTO recurso) {
        Provincia entidad = mapper.dtoToEntity(recurso);
        Provincia guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProvinciaDTO recurso) {
        Provincia entidad = mapper.dtoToEntity(recurso);
        Provincia actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
