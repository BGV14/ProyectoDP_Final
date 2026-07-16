package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.ApoderadoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Apoderado;

@Mapper(componentModel = "spring")
public interface ApoderadoMapper extends GenericMapper<ApoderadoDTO, Apoderado> {}