package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Empleado and its DTO EmpleadoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProveedorMapper.class, UserMapper.class, PersonaMapper.class, AdscripcionMapper.class})
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {

    @Mapping(source = "proveedor.id", target = "proveedorId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "persona.id", target = "personaId")
    EmpleadoDTO toDto(Empleado empleado);

    @Mapping(source = "proveedorId", target = "proveedor")
    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(source = "personaId", target = "persona")
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
