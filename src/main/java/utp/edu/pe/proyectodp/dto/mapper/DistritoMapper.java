package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.DistritoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Distrito;

@Mapper(componentModel = "spring")
public interface DistritoMapper extends GenericMapper<DistritoDTO, Distrito> {}