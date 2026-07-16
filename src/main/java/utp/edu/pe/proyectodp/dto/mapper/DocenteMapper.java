package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.DocenteDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper extends GenericMapper<DocenteDTO, Docente> {}