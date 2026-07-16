package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.PagoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Pago;

@Mapper(componentModel = "spring")
public interface PagoMapper extends GenericMapper<PagoDTO, Pago> {}