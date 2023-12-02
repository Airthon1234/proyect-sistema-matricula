package pe.edu.cibertec.proyectsistemamatricula.model.bd;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "alumno_id")
    private Integer alumnoId;

    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "profesor_id")
    private Integer profesorId;

    @Column(name = "fecha_matricula")
    private LocalDateTime fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
}
