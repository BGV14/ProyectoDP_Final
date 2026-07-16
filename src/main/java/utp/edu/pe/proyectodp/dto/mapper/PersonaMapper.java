package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.PersonaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper extends GenericMapper<PersonaDTO, Persona> {}