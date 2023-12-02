package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Profesor;

import pe.edu.cibertec.proyectsistemamatricula.service.ProfesorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/profesores")
public class ProfesorController {

    private ProfesorService profesorService;

    @GetMapping("")
    public ResponseEntity<List<Profesor>> listarProfesores() {
        List<Profesor> profesorList = new ArrayList<>();
        profesorService.listarProfesores().forEach(profesorList::add);
        if (profesorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesor(@PathVariable Integer id) {
        Optional<Profesor> profesor = profesorService.obtenerProfesorPorId(id);
        return profesor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Profesor> guardarProfesor(
            @RequestParam(name = "profesorid", required = false) Integer profesorid,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "apellido") String apellido,
            @RequestParam(name = "especialidad") String especialidad,
            @RequestParam(name = "correo") String correo) {

        Profesor profesor = new Profesor(profesorid, nombre, apellido, especialidad, correo);
        Profesor nuevoProfesor = profesorService.guardarProfesor(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable Integer id, @RequestBody Profesor profesor) {
        Optional<Profesor> profesorExistente = profesorService.obtenerProfesorPorId(id);
        if (profesorExistente.isPresent()) {
            profesor.setProfesorid(id);
            Profesor profesorActualizado = profesorService.guardarProfesor(profesor);
            return new ResponseEntity<>(profesorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable Integer id) {
        Optional<Profesor> profesorExistente = profesorService.obtenerProfesorPorId(id);

        if (profesorExistente.isPresent()) {
            profesorService.eliminarProfesor(id);
            return new ResponseEntity<>("Profesor eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profesor no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
