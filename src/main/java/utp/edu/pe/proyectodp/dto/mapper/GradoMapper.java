package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.GradoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Grado;

@Mapper(componentModel = "spring")
public interface GradoMapper extends GenericMapper<GradoDTO, Grado> {}