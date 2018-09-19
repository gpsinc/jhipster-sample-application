package gps.mx.poc.start.jhipster.sample.service.impl;

import gps.mx.poc.start.jhipster.sample.service.EmpleadoService;
import gps.mx.poc.start.jhipster.sample.domain.Empleado;
import gps.mx.poc.start.jhipster.sample.repository.EmpleadoRepository;
import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.EmpleadoMapper;
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
 * Service Implementation for managing Empleado.
 */
@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final Logger log = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    /**
     * Save a empleado.
     *
     * @param empleadoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
        log.debug("Request to save Empleado : {}", empleadoDTO);
        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(empleado);
    }

    /**
     * Get all the empleados.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> findAll() {
        log.debug("Request to get all Empleados");
        return empleadoRepository.findAllWithEagerRelationships().stream()
            .map(empleadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Empleado with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EmpleadoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return empleadoRepository.findAllWithEagerRelationships(pageable).map(empleadoMapper::toDto);
    }
    

    /**
     * Get one empleado by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoDTO> findOne(Long id) {
        log.debug("Request to get Empleado : {}", id);
        return empleadoRepository.findOneWithEagerRelationships(id)
            .map(empleadoMapper::toDto);
    }

    /**
     * Delete the empleado by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Empleado : {}", id);
        empleadoRepository.deleteById(id);
    }
}
