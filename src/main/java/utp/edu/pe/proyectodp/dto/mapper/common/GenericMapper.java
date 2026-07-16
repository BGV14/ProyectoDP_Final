package utp.edu.pe.proyectodp.dto.mapper.common;

import java.util.List;
import org.mapstruct.MappingTarget;

public interface GenericMapper<D, E> {
    E dtoToEntity(D dto);
    D entityToDto(E entity);
    List<E> dtoToEntity(Iterable<D> dtos);
    List<D> entityToDto(Iterable<E> entities);
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}