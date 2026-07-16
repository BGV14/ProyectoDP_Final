package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.EstudianteDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Estudiante;

// dto/mapper/EstudianteMapper.java
@Mapper(componentModel = "spring")
public interface EstudianteMapper extends GenericMapper<EstudianteDTO, Estudiante> {
}
