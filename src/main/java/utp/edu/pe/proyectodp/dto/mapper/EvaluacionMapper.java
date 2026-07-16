package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.EvaluacionDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Evaluacion;

@Mapper(componentModel = "spring")
public interface EvaluacionMapper extends GenericMapper<EvaluacionDTO, Evaluacion> {}