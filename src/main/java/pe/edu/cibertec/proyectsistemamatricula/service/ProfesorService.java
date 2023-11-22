package pe.edu.cibertec.proyectsistemamatricula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Profesor;
import pe.edu.cibertec.proyectsistemamatricula.repository.CursoRepository;
import pe.edu.cibertec.proyectsistemamatricula.repository.ProfesorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerProfesorPorId(Integer id) {
        return profesorRepository.findByProfesorid(id);
    }

    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void eliminarProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }
}
