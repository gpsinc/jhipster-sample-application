package gps.mx.poc.start.jhipster.sample.service;

import gps.mx.poc.start.jhipster.sample.service.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Persona.
 */
public interface PersonaService {

    /**
     * Save a persona.
     *
     * @param personaDTO the entity to save
     * @return the persisted entity
     */
    PersonaDTO save(PersonaDTO personaDTO);

    /**
     * Get all the personas.
     *
     * @return the list of entities
     */
    List<PersonaDTO> findAll();


    /**
     * Get the "id" persona.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PersonaDTO> findOne(Long id);

    /**
     * Delete the "id" persona.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
