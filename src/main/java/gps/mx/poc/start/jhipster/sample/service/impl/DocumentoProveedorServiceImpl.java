package gps.mx.poc.start.jhipster.sample.service.impl;

import gps.mx.poc.start.jhipster.sample.service.DocumentoProveedorService;
import gps.mx.poc.start.jhipster.sample.domain.DocumentoProveedor;
import gps.mx.poc.start.jhipster.sample.repository.DocumentoProveedorRepository;
import gps.mx.poc.start.jhipster.sample.service.dto.DocumentoProveedorDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.DocumentoProveedorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DocumentoProveedor.
 */
@Service
@Transactional
public class DocumentoProveedorServiceImpl implements DocumentoProveedorService {

    private final Logger log = LoggerFactory.getLogger(DocumentoProveedorServiceImpl.class);

    private final DocumentoProveedorRepository documentoProveedorRepository;

    private final DocumentoProveedorMapper documentoProveedorMapper;

    public DocumentoProveedorServiceImpl(DocumentoProveedorRepository documentoProveedorRepository, DocumentoProveedorMapper documentoProveedorMapper) {
        this.documentoProveedorRepository = documentoProveedorRepository;
        this.documentoProveedorMapper = documentoProveedorMapper;
    }

    /**
     * Save a documentoProveedor.
     *
     * @param documentoProveedorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DocumentoProveedorDTO save(DocumentoProveedorDTO documentoProveedorDTO) {
        log.debug("Request to save DocumentoProveedor : {}", documentoProveedorDTO);
        DocumentoProveedor documentoProveedor = documentoProveedorMapper.toEntity(documentoProveedorDTO);
        documentoProveedor = documentoProveedorRepository.save(documentoProveedor);
        return documentoProveedorMapper.toDto(documentoProveedor);
    }

    /**
     * Get all the documentoProveedors.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentoProveedorDTO> findAll() {
        log.debug("Request to get all DocumentoProveedors");
        return documentoProveedorRepository.findAll().stream()
            .map(documentoProveedorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one documentoProveedor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentoProveedorDTO> findOne(Long id) {
        log.debug("Request to get DocumentoProveedor : {}", id);
        return documentoProveedorRepository.findById(id)
            .map(documentoProveedorMapper::toDto);
    }

    /**
     * Delete the documentoProveedor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentoProveedor : {}", id);
        documentoProveedorRepository.deleteById(id);
    }
}
