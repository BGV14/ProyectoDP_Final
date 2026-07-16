package utp.edu.pe.proyectodp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.dto.AdministradorDTO;
import utp.edu.pe.proyectodp.dto.mapper.AdministradorMapper;
import utp.edu.pe.proyectodp.entity.Administrador;
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.AdministradorService;

import java.util.List;

/**
 * Expone las operaciones CRUD de {@link Administrador} que ya existian en
 * {@link AdministradorService} pero que no estaban disponibles vía REST.
 *
 * Usa {@link AdministradorMapper} para responder siempre con {@link AdministradorDTO},
 * que oculta el campo password (ver @Mapping(target = "password", ignore = true)).
 * Nunca se devuelve la entidad Administrador directamente en el body de la respuesta.
 */
@Slf4j
@Tag(name = "Administradores", description = "Gestión de administradores del sistema")
@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService service;
    private final AdministradorMapper mapper;

    @GetMapping
    public List<AdministradorDTO> listar() {
        return mapper.entityToDto(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Administrador no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> registrar(@Valid @RequestBody AdministradorDTO recurso) {
        Administrador entidad = mapper.dtoToEntity(recurso);
        Administrador guardado = service.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AdministradorDTO recurso) {
        Administrador entidad = mapper.dtoToEntity(recurso);
        Administrador actualizado = service.actualizar(id, entidad);
        return ResponseEntity.ok(mapper.entityToDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}