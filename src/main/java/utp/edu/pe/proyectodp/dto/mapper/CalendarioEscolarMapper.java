package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.CalendarioEscolarDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.CalendarioEscolar;

@Mapper(componentModel = "spring")
public interface CalendarioEscolarMapper extends GenericMapper<CalendarioEscolarDTO, CalendarioEscolar> {}