package gps.mx.poc.start.jhipster.sample.service;

import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionResponsableDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AdscripcionResponsable.
 */
public interface AdscripcionResponsableService {

    /**
     * Save a adscripcionResponsable.
     *
     * @param adscripcionResponsableDTO the entity to save
     * @return the persisted entity
     */
    AdscripcionResponsableDTO save(AdscripcionResponsableDTO adscripcionResponsableDTO);

    /**
     * Get all the adscripcionResponsables.
     *
     * @return the list of entities
     */
    List<AdscripcionResponsableDTO> findAll();


    /**
     * Get the "id" adscripcionResponsable.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdscripcionResponsableDTO> findOne(Long id);

    /**
     * Delete the "id" adscripcionResponsable.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
