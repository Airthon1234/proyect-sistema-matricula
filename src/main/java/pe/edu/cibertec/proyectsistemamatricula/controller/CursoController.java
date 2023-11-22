package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Curso;
import pe.edu.cibertec.proyectsistemamatricula.service.CursoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cursos")
public class CursoController {

    private CursoService cursoService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> listarcurso() {
        List<Curso> cursoList = new ArrayList<>();
        cursoService.listarCursos().forEach(cursoList::add);
        if (cursoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cursoList, HttpStatus.OK);
    }
}