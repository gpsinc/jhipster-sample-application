package gps.mx.poc.start.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import gps.mx.poc.start.jhipster.sample.service.DocumentoProveedorService;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.BadRequestAlertException;
import gps.mx.poc.start.jhipster.sample.web.rest.util.HeaderUtil;
import gps.mx.poc.start.jhipster.sample.service.dto.DocumentoProveedorDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DocumentoProveedor.
 */
@RestController
@RequestMapping("/api")
public class DocumentoProveedorResource {

    private final Logger log = LoggerFactory.getLogger(DocumentoProveedorResource.class);

    private static final String ENTITY_NAME = "documentoProveedor";

    private final DocumentoProveedorService documentoProveedorService;

    public DocumentoProveedorResource(DocumentoProveedorService documentoProveedorService) {
        this.documentoProveedorService = documentoProveedorService;
    }

    /**
     * POST  /documento-proveedors : Create a new documentoProveedor.
     *
     * @param documentoProveedorDTO the documentoProveedorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentoProveedorDTO, or with status 400 (Bad Request) if the documentoProveedor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documento-proveedors")
    @Timed
    public ResponseEntity<DocumentoProveedorDTO> createDocumentoProveedor(@RequestBody DocumentoProveedorDTO documentoProveedorDTO) throws URISyntaxException {
        log.debug("REST request to save DocumentoProveedor : {}", documentoProveedorDTO);
        if (documentoProveedorDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentoProveedor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentoProveedorDTO result = documentoProveedorService.save(documentoProveedorDTO);
        return ResponseEntity.created(new URI("/api/documento-proveedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /documento-proveedors : Updates an existing documentoProveedor.
     *
     * @param documentoProveedorDTO the documentoProveedorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentoProveedorDTO,
     * or with status 400 (Bad Request) if the documentoProveedorDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentoProveedorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documento-proveedors")
    @Timed
    public ResponseEntity<DocumentoProveedorDTO> updateDocumentoProveedor(@RequestBody DocumentoProveedorDTO documentoProveedorDTO) throws URISyntaxException {
        log.debug("REST request to update DocumentoProveedor : {}", documentoProveedorDTO);
        if (documentoProveedorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentoProveedorDTO result = documentoProveedorService.save(documentoProveedorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentoProveedorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documento-proveedors : get all the documentoProveedors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of documentoProveedors in body
     */
    @GetMapping("/documento-proveedors")
    @Timed
    public List<DocumentoProveedorDTO> getAllDocumentoProveedors() {
        log.debug("REST request to get all DocumentoProveedors");
        return documentoProveedorService.findAll();
    }

    /**
     * GET  /documento-proveedors/:id : get the "id" documentoProveedor.
     *
     * @param id the id of the documentoProveedorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentoProveedorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/documento-proveedors/{id}")
    @Timed
    public ResponseEntity<DocumentoProveedorDTO> getDocumentoProveedor(@PathVariable Long id) {
        log.debug("REST request to get DocumentoProveedor : {}", id);
        Optional<DocumentoProveedorDTO> documentoProveedorDTO = documentoProveedorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentoProveedorDTO);
    }

    /**
     * DELETE  /documento-proveedors/:id : delete the "id" documentoProveedor.
     *
     * @param id the id of the documentoProveedorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documento-proveedors/{id}")
    @Timed
    public ResponseEntity<Void> deleteDocumentoProveedor(@PathVariable Long id) {
        log.debug("REST request to delete DocumentoProveedor : {}", id);
        documentoProveedorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
