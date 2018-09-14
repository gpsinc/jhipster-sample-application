package gps.mx.poc.start.jhipster.sample.service.impl;

import gps.mx.poc.start.jhipster.sample.service.PersonaService;
import gps.mx.poc.start.jhipster.sample.domain.Persona;
import gps.mx.poc.start.jhipster.sample.repository.PersonaRepository;
import gps.mx.poc.start.jhipster.sample.service.dto.PersonaDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.PersonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Persona.
 */
@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    /**
     * Save a persona.
     *
     * @param personaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonaDTO save(PersonaDTO personaDTO) {
        log.debug("Request to save Persona : {}", personaDTO);
        Persona persona = personaMapper.toEntity(personaDTO);
        persona = personaRepository.save(persona);
        return personaMapper.toDto(persona);
    }

    /**
     * Get all the personas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO> findAll() {
        log.debug("Request to get all Personas");
        return personaRepository.findAll().stream()
            .map(personaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one persona by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonaDTO> findOne(Long id) {
        log.debug("Request to get Persona : {}", id);
        return personaRepository.findById(id)
            .map(personaMapper::toDto);
    }

    /**
     * Delete the persona by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Persona : {}", id);
        personaRepository.deleteById(id);
    }
}
