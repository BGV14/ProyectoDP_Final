package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.DepartamentoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Departamento;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper extends GenericMapper<DepartamentoDTO, Departamento> {}