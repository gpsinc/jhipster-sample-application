package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionResponsableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdscripcionResponsable and its DTO AdscripcionResponsableDTO.
 */
@Mapper(componentModel = "spring", uses = {ProveedorMapper.class})
public interface AdscripcionResponsableMapper extends EntityMapper<AdscripcionResponsableDTO, AdscripcionResponsable> {

    @Mapping(source = "proveedor.id", target = "proveedorId")
    AdscripcionResponsableDTO toDto(AdscripcionResponsable adscripcionResponsable);

    @Mapping(source = "proveedorId", target = "proveedor")
    @Mapping(target = "responsables", ignore = true)
    @Mapping(target = "adscripcions", ignore = true)
    AdscripcionResponsable toEntity(AdscripcionResponsableDTO adscripcionResponsableDTO);

    default AdscripcionResponsable fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdscripcionResponsable adscripcionResponsable = new AdscripcionResponsable();
        adscripcionResponsable.setId(id);
        return adscripcionResponsable;
    }
}
