package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.SeccionDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Seccion;

@Mapper(componentModel = "spring")
public interface SeccionMapper extends GenericMapper<SeccionDTO, Seccion> {}