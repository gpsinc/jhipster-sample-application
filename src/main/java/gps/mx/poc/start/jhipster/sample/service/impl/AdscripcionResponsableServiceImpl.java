package gps.mx.poc.start.jhipster.sample.service.impl;

import gps.mx.poc.start.jhipster.sample.service.AdscripcionResponsableService;
import gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable;
import gps.mx.poc.start.jhipster.sample.repository.AdscripcionResponsableRepository;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionResponsableDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.AdscripcionResponsableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AdscripcionResponsable.
 */
@Service
@Transactional
public class AdscripcionResponsableServiceImpl implements AdscripcionResponsableService {

    private final Logger log = LoggerFactory.getLogger(AdscripcionResponsableServiceImpl.class);

    private final AdscripcionResponsableRepository adscripcionResponsableRepository;

    private final AdscripcionResponsableMapper adscripcionResponsableMapper;

    public AdscripcionResponsableServiceImpl(AdscripcionResponsableRepository adscripcionResponsableRepository, AdscripcionResponsableMapper adscripcionResponsableMapper) {
        this.adscripcionResponsableRepository = adscripcionResponsableRepository;
        this.adscripcionResponsableMapper = adscripcionResponsableMapper;
    }

    /**
     * Save a adscripcionResponsable.
     *
     * @param adscripcionResponsableDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdscripcionResponsableDTO save(AdscripcionResponsableDTO adscripcionResponsableDTO) {
        log.debug("Request to save AdscripcionResponsable : {}", adscripcionResponsableDTO);
        AdscripcionResponsable adscripcionResponsable = adscripcionResponsableMapper.toEntity(adscripcionResponsableDTO);
        adscripcionResponsable = adscripcionResponsableRepository.save(adscripcionResponsable);
        return adscripcionResponsableMapper.toDto(adscripcionResponsable);
    }

    /**
     * Get all the adscripcionResponsables.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdscripcionResponsableDTO> findAll() {
        log.debug("Request to get all AdscripcionResponsables");
        return adscripcionResponsableRepository.findAll().stream()
            .map(adscripcionResponsableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adscripcionResponsable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdscripcionResponsableDTO> findOne(Long id) {
        log.debug("Request to get AdscripcionResponsable : {}", id);
        return adscripcionResponsableRepository.findById(id)
            .map(adscripcionResponsableMapper::toDto);
    }

    /**
     * Delete the adscripcionResponsable by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdscripcionResponsable : {}", id);
        adscripcionResponsableRepository.deleteById(id);
    }
}
