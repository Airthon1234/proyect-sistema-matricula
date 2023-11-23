package pe.edu.cibertec.proyectsistemamatricula.model.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlumnoDto implements DtoEntity{

    private Integer alumnoid;
    private String nombre;
    private String apellido;
    private int edad;
}
