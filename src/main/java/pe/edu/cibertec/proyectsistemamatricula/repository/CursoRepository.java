package pe.edu.cibertec.proyectsistemamatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Alumno;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Curso;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Optional<Curso> findByCursoid(Integer id);
}
