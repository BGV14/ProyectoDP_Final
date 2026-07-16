package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.ProvinciaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Provincia;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper extends GenericMapper<ProvinciaDTO, Provincia> {}