package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.DirectorDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Director;

@Mapper(componentModel = "spring")
public interface DirectorMapper extends GenericMapper<DirectorDTO, Director> {}