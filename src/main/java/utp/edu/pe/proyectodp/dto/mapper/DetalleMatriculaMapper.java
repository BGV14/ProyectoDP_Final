package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.DetalleMatriculaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.DetalleMatricula;

@Mapper(componentModel = "spring")
public interface DetalleMatriculaMapper extends GenericMapper<DetalleMatriculaDTO, DetalleMatricula> {}