package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.DireccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Direccion and its DTO DireccionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DireccionMapper extends EntityMapper<DireccionDTO, Direccion> {



    default Direccion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Direccion direccion = new Direccion();
        direccion.setId(id);
        return direccion;
    }
}
