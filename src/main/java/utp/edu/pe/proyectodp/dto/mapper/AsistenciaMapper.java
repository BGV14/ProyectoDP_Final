package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.AsistenciaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Asistencia;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper extends GenericMapper<AsistenciaDTO, Asistencia> {}