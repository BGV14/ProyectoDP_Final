package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.NotaDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Nota;

@Mapper(componentModel = "spring")
public interface NotaMapper extends GenericMapper<NotaDTO, Nota> {}