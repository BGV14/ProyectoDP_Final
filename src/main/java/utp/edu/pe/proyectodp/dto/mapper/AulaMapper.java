package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.AulaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Aula;

@Mapper(componentModel = "spring")
public interface AulaMapper extends GenericMapper<AulaDTO, Aula> {}