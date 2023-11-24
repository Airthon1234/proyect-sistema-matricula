package pe.edu.cibertec.proyectsistemamatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.proyectsistemamatricula.model.bd.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNomusuario(String nomusuario);
}
