package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Curso;
import pe.edu.cibertec.proyectsistemamatricula.service.CursoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();

        if (cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/guardar/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Integer id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
        return curso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Curso> guardarCurso(
            @RequestParam(name = "cursoid", required = false) Integer cursoid,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "creditos") int creditos) {

        Curso curso = new Curso(cursoid, nombre, descripcion, creditos);
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        Optional<Curso> cursoExistente = cursoService.obtenerCursoPorId(id);
        if (cursoExistente.isPresent()) {
            curso.setCursoid(id);
            Curso cursoActualizado = cursoService.guardarCurso(curso);
            return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Integer id) {
        Optional<Curso> cursoExistente = cursoService.obtenerCursoPorId(id);
        if (cursoExistente.isPresent()) {
            cursoService.eliminarCurso(id);
            return new ResponseEntity<>("Curso eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Curso no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}