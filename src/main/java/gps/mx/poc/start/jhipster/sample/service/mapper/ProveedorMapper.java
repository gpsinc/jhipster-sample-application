package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.ProveedorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Proveedor and its DTO ProveedorDTO.
 */
@Mapper(componentModel = "spring", uses = {DireccionMapper.class, PersonaMapper.class, UserMapper.class, AdscripcionMapper.class})
public interface ProveedorMapper extends EntityMapper<ProveedorDTO, Proveedor> {

    @Mapping(source = "direccion.id", target = "direccionId")
    @Mapping(source = "persona.id", target = "personaId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    ProveedorDTO toDto(Proveedor proveedor);

    @Mapping(source = "direccionId", target = "direccion")
    @Mapping(source = "personaId", target = "persona")
    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "adscripciones", ignore = true)
    Proveedor toEntity(ProveedorDTO proveedorDTO);

    default Proveedor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        return proveedor;
    }
}
