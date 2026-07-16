import os

# Ruta del paquete de controladores dentro de tu proyecto.
# Ajusta si tu estructura de carpetas es distinta.
BASE = "src/main/java/utp/edu/pe/proyectodp/controller"
os.makedirs(BASE, exist_ok=True)

# entidad -> segmento de ruta REST (plural, en minúsculas, con guiones)
entidades = {
    "Administrador": "administradores",
    "AnioEscolar": "anios-escolares",
    "Apoderado": "apoderados",
    "Asistencia": "asistencias",
    "Aula": "aulas",
    "Boleta": "boletas",
    "CalendarioEscolar": "calendarios-escolares",
    "Curso": "cursos",
    "Departamento": "departamentos",
    "DetalleMatricula": "detalle-matriculas",
    "Director": "directores",
    "Distrito": "distritos",
    "Docente": "docentes",
    "Estudiante": "estudiantes",
    "Evaluacion": "evaluaciones",
    "Grado": "grados",
    "Horario": "horarios",
    "Libreta": "libretas",
    "Matricula": "matriculas",
    "MetodoPago": "metodos-pago",
    "Nota": "notas",
    "Persona": "personas",
    "Provincia": "provincias",
    "Seccion": "secciones",
}

PLANTILLA = """package utp.edu.pe.proyectodp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.proyectodp.entity.{entidad};
import utp.edu.pe.proyectodp.exception.RecursoNoEncontradoException;
import utp.edu.pe.proyectodp.service.{entidad}Service;

import java.util.List;

/**
 * Expone las operaciones CRUD de {{@link {entidad}}} que ya existian en
 * {{@link {entidad}Service}} pero que no estaban disponibles vía REST.
 */
@Slf4j
@RestController
@RequestMapping("/api/{ruta}")
@RequiredArgsConstructor
public class {entidad}Controller {{

    private final {entidad}Service service;

    @GetMapping
    public List<{entidad}> listar() {{
        return service.listar();
    }}

    @GetMapping("/{{id}}")
    public ResponseEntity<{entidad}> buscarPorId(@PathVariable Long id) {{
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "{entidad} no encontrado con id " + id));
    }}

    @PostMapping
    public ResponseEntity<{entidad}> registrar(@RequestBody {entidad} recurso) {{
        {entidad} guardado = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }}

    @PutMapping("/{{id}}")
    public ResponseEntity<{entidad}> actualizar(@PathVariable Long id, @RequestBody {entidad} recurso) {{
        return ResponseEntity.ok(service.actualizar(id, recurso));
    }}

    @DeleteMapping("/{{id}}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }}
}}
"""

generados = 0
for entidad, ruta in entidades.items():
    destino = os.path.join(BASE, f"{entidad}Controller.java")
    if os.path.exists(destino):
        print(f"Ya existe, se omite: {destino}")
        continue
    contenido = PLANTILLA.format(entidad=entidad, ruta=ruta)
    with open(destino, "w", newline="\n", encoding="utf-8") as f:
        f.write(contenido)
    print(f"Creado: {destino}")
    generados += 1

print(f"\nTotal de controladores generados: {generados}")