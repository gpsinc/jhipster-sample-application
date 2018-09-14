package gps.mx.poc.start.jhipster.sample.service;

import gps.mx.poc.start.jhipster.sample.service.dto.DocumentoProveedorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DocumentoProveedor.
 */
public interface DocumentoProveedorService {

    /**
     * Save a documentoProveedor.
     *
     * @param documentoProveedorDTO the entity to save
     * @return the persisted entity
     */
    DocumentoProveedorDTO save(DocumentoProveedorDTO documentoProveedorDTO);

    /**
     * Get all the documentoProveedors.
     *
     * @return the list of entities
     */
    List<DocumentoProveedorDTO> findAll();


    /**
     * Get the "id" documentoProveedor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DocumentoProveedorDTO> findOne(Long id);

    /**
     * Delete the "id" documentoProveedor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
