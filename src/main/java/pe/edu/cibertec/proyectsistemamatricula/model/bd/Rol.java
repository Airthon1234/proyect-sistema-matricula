package pe.edu.cibertec.proyectsistemamatricula.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;
    @Column(name = "nomrol")
    private String nomrol;
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}
