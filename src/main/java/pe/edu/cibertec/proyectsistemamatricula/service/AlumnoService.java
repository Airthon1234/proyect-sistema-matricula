package pe.edu.cibertec.proyectsistemamatricula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.repository.AlumnoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> listarAlumno() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> obtenerAlumnoPorId(Integer id) {
        return alumnoRepository.findByAlumnoid(id);
    }

    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void eliminarAlumno(Integer id) {
        alumnoRepository.deleteById(id);
    }

    public Optional<Alumno> obtenerAlumnoPorNombre(String nombre) {
        return alumnoRepository.findByNombre(nombre);
    }

    public List<Alumno> buscarAlumnoPorNombre(String nombre) {
        return alumnoRepository.findAllByNombre(nombre);
    }

}
