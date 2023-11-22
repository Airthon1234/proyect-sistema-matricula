package pe.edu.cibertec.proyectsistemamatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Profesor;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

    Optional<Profesor> findByProfesorid(Integer id);
}
