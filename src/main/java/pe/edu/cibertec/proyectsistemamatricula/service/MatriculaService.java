package pe.edu.cibertec.proyectsistemamatricula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Matricula;
import pe.edu.cibertec.proyectsistemamatricula.repository.MatriculaRepository;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    public Matricula getMatriculaById(Integer id) {
        return matriculaRepository.findById(id).get();
    }

    public Matricula saveMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public void deleteMatricula(Integer id) {
        matriculaRepository.deleteById(id);
    }
}
