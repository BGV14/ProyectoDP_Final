package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;

@Mapper(componentModel = "spring")
public interface AnioEscolarMapper extends GenericMapper<utp.edu.pe.proyectodp.dto.AnioEscolarDTO, utp.edu.pe.proyectodp.entity.AnioEscolar> {}