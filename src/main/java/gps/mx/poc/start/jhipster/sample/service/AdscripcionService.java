package gps.mx.poc.start.jhipster.sample.service;

import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Adscripcion.
 */
public interface AdscripcionService {

    /**
     * Save a adscripcion.
     *
     * @param adscripcionDTO the entity to save
     * @return the persisted entity
     */
    AdscripcionDTO save(AdscripcionDTO adscripcionDTO);

    /**
     * Get all the adscripcions.
     *
     * @return the list of entities
     */
    List<AdscripcionDTO> findAll();

    /**
     * Get all the Adscripcion with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<AdscripcionDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" adscripcion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdscripcionDTO> findOne(Long id);

    /**
     * Delete the "id" adscripcion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
