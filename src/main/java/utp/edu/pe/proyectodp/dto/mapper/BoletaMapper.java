package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.BoletaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Boleta;

@Mapper(componentModel = "spring")
public interface BoletaMapper extends GenericMapper<BoletaDTO, Boleta> {}