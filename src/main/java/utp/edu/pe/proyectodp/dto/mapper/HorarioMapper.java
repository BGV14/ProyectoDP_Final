package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.HorarioDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Horario;

@Mapper(componentModel = "spring")
public interface HorarioMapper extends GenericMapper<HorarioDTO, Horario> {}