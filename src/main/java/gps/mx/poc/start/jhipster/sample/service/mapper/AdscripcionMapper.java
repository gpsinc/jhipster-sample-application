package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Adscripcion and its DTO AdscripcionDTO.
 */
@Mapper(componentModel = "spring", uses = {ProveedorMapper.class, EmpleadoMapper.class})
public interface AdscripcionMapper extends EntityMapper<AdscripcionDTO, Adscripcion> {

    @Mapping(source = "proveedor.id", target = "proveedorId")
    AdscripcionDTO toDto(Adscripcion adscripcion);

    @Mapping(source = "proveedorId", target = "proveedor")
    Adscripcion toEntity(AdscripcionDTO adscripcionDTO);

    default Adscripcion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Adscripcion adscripcion = new Adscripcion();
        adscripcion.setId(id);
        return adscripcion;
    }
}
