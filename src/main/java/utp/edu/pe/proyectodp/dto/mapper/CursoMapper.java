package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.CursoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper extends GenericMapper<CursoDTO, Curso> {}