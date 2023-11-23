package pe.edu.cibertec.proyectsistemamatricula.model.request;

import lombok.Builder;
import lombok.Data;

@Data
public class AlumnoRequest {

    private String nombre;
    private String apellido;
    private int edad;
}
