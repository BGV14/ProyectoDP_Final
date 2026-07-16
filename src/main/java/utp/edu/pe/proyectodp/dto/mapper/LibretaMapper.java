package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.LibretaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Libreta;

@Mapper(componentModel = "spring")
public interface LibretaMapper extends GenericMapper<LibretaDTO, Libreta> {}