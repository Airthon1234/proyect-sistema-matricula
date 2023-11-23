package pe.edu.cibertec.proyectsistemamatricula.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfesorDto implements DtoEntity{

    private Integer profesorid;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String correo;
}
