package pe.edu.cibertec.proyectsistemamatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
