package pe.edu.cibertec.proyectsistemamatricula.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.proyectsistemamatricula.model.dto.DtoEntity;

@Component
public class DtoUtil {

    public DtoEntity convertirDto(Object obj, DtoEntity mapper){
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertirAEntidad(Object obj, DtoEntity mapper){
        return new ModelMapper().map(mapper, obj.getClass());
    }
}
