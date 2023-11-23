package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.model.dto.AlumnoDto;
import pe.edu.cibertec.proyectsistemamatricula.service.AlumnoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/dto")
    public ResponseEntity<List<AlumnoDto>> listarAlumnoDto(){
        List<AlumnoDto> alumnoDtoList = alumnoService.listarAlumno()
                .stream()
                .map(alumno -> new AlumnoDto(alumno.getAlumnoid(), alumno.getNombre(), alumno.getApellido(), alumno.getEdad()))
                .collect(Collectors.toList());

        if (alumnoDtoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(alumnoDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Integer id) {
        Optional<Alumno> alumno = alumnoService.obtenerAlumnoPorId(id);
        return alumno.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Alumno> guardarAlumno(
            @RequestParam(name = "alumnoid", required = false) Integer alumnoid,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "apellido") String apellido,
            @RequestParam(name = "edad") int edad) {

        Alumno alumno = new Alumno(alumnoid, nombre, apellido, edad);
        Alumno nuevoAlumno = alumnoService.guardarAlumno(alumno);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoExistente = alumnoService.obtenerAlumnoPorId(id);

        if (alumnoExistente.isPresent()) {
            alumno.setAlumnoid(id);
            Alumno alumnoActualizado = alumnoService.guardarAlumno(alumno);
            return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAlumno(@PathVariable Integer id) {
        Optional<Alumno> alumnoExistente = alumnoService.obtenerAlumnoPorId(id);

        if (alumnoExistente.isPresent()) {
            alumnoService.eliminarAlumno(id);
            return new ResponseEntity<>("Alumno eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
