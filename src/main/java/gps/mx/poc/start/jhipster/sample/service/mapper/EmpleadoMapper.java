package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Empleado and its DTO EmpleadoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PersonaMapper.class, AdscripcionMapper.class, AdscripcionResponsableMapper.class})
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "persona.id", target = "personaId")
    @Mapping(source = "adscripcionResponsable.id", target = "adscripcionResponsableId")
    EmpleadoDTO toDto(Empleado empleado);

    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(source = "personaId", target = "persona")
    @Mapping(source = "adscripcionResponsableId", target = "adscripcionResponsable")
    Empleado toEntity(EmpleadoDTO empleadoDTO);

    default Empleado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
    }
}
