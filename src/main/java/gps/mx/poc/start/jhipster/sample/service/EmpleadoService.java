package gps.mx.poc.start.jhipster.sample.service;

import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Empleado.
 */
public interface EmpleadoService {

    /**
     * Save a empleado.
     *
     * @param empleadoDTO the entity to save
     * @return the persisted entity
     */
    EmpleadoDTO save(EmpleadoDTO empleadoDTO);

    /**
     * Get all the empleados.
     *
     * @return the list of entities
     */
    List<EmpleadoDTO> findAll();

    /**
     * Get all the Empleado with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EmpleadoDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" empleado.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmpleadoDTO> findOne(Long id);

    /**
     * Delete the "id" empleado.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
