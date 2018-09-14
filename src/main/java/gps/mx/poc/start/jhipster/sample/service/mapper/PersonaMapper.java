package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.PersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Persona and its DTO PersonaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {



    default Persona fromId(Long id) {
        if (id == null) {
            return null;
        }
        Persona persona = new Persona();
        persona.setId(id);
        return persona;
    }
}
