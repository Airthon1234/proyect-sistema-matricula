package pe.edu.cibertec.proyectsistemamatricula.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoDto implements DtoEntity{

    private Integer cursoid;
    private String nombre;
    private String descripcion;
    private int creditos;
}
