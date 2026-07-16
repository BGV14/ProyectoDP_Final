package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import utp.edu.pe.proyectodp.dto.MetodoPagoDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.MetodoPago;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper extends GenericMapper<MetodoPagoDTO, MetodoPago> {
}