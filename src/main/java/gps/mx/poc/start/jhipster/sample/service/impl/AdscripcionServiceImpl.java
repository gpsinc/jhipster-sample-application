package gps.mx.poc.start.jhipster.sample.service.impl;

import gps.mx.poc.start.jhipster.sample.service.AdscripcionService;
import gps.mx.poc.start.jhipster.sample.domain.Adscripcion;
import gps.mx.poc.start.jhipster.sample.repository.AdscripcionRepository;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.AdscripcionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Adscripcion.
 */
@Service
@Transactional
public class AdscripcionServiceImpl implements AdscripcionService {

    private final Logger log = LoggerFactory.getLogger(AdscripcionServiceImpl.class);

    private final AdscripcionRepository adscripcionRepository;

    private final AdscripcionMapper adscripcionMapper;

    public AdscripcionServiceImpl(AdscripcionRepository adscripcionRepository, AdscripcionMapper adscripcionMapper) {
        this.adscripcionRepository = adscripcionRepository;
        this.adscripcionMapper = adscripcionMapper;
    }

    /**
     * Save a adscripcion.
     *
     * @param adscripcionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdscripcionDTO save(AdscripcionDTO adscripcionDTO) {
        log.debug("Request to save Adscripcion : {}", adscripcionDTO);
        Adscripcion adscripcion = adscripcionMapper.toEntity(adscripcionDTO);
        adscripcion = adscripcionRepository.save(adscripcion);
        return adscripcionMapper.toDto(adscripcion);
    }

    /**
     * Get all the adscripcions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdscripcionDTO> findAll() {
        log.debug("Request to get all Adscripcions");
        return adscripcionRepository.findAllWithEagerRelationships().stream()
            .map(adscripcionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Adscripcion with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<AdscripcionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return adscripcionRepository.findAllWithEagerRelationships(pageable).map(adscripcionMapper::toDto);
    }
    

    /**
     * Get one adscripcion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdscripcionDTO> findOne(Long id) {
        log.debug("Request to get Adscripcion : {}", id);
        return adscripcionRepository.findOneWithEagerRelationships(id)
            .map(adscripcionMapper::toDto);
    }

    /**
     * Delete the adscripcion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Adscripcion : {}", id);
        adscripcionRepository.deleteById(id);
    }
}
