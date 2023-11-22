package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.service.AlumnoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("")
    public ResponseEntity<List<Alumno>> listarAlumno() {
        List<Alumno> alumnoList = new ArrayList<>();
        alumnoService.listarAlumno().forEach(alumnoList::add);
        if (alumnoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alumnoList, HttpStatus.OK);
    }
}
