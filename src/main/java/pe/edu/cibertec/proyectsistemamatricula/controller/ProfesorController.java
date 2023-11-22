package pe.edu.cibertec.proyectsistemamatricula.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Profesor;
import pe.edu.cibertec.proyectsistemamatricula.service.ProfesorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/profesores")
public class ProfesorController {

    private ProfesorService profesorService;

    @GetMapping("")
    public ResponseEntity<List<Profesor>> listarprofesor(){
        List<Profesor> profesorList = new ArrayList<>();
        profesorService.listarProfesores().forEach(profesorList::add);
        if (profesorList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesorList, HttpStatus.OK);
    }
}
