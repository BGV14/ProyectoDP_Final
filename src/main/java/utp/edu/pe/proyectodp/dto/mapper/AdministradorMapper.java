package utp.edu.pe.proyectodp.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import utp.edu.pe.proyectodp.dto.AdministradorDTO;
import utp.edu.pe.proyectodp.dto.mapper.common.GenericMapper;
import utp.edu.pe.proyectodp.entity.Administrador;

@Mapper(componentModel = "spring")
public interface AdministradorMapper extends GenericMapper<AdministradorDTO, Administrador> {

    @Override
    @Mapping(target = "password", ignore = true)
    AdministradorDTO entityToDto(Administrador entity);
}