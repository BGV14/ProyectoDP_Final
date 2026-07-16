package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.MatriculaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Matricula;

@Mapper(componentModel = "spring")
public interface MatriculaMapper extends GenericMapper<MatriculaDTO, Matricula> {}