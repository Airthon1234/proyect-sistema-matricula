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

@RestController
@AllArgsConstructor
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursoList = new ArrayList<>();
        cursoService.listarCursos().forEach(cursoList::add);
        if (cursoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cursoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        Optional<Curso> cursoExistente = cursoService.obtenerCursoPorId(id);
        if (cursoExistente.isPresent()) {
            cursoService.eliminarCurso(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}