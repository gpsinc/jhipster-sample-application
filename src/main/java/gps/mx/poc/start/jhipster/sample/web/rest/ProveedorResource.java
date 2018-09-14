package gps.mx.poc.start.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import gps.mx.poc.start.jhipster.sample.service.ProveedorService;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.BadRequestAlertException;
import gps.mx.poc.start.jhipster.sample.web.rest.util.HeaderUtil;
import gps.mx.poc.start.jhipster.sample.web.rest.util.PaginationUtil;
import gps.mx.poc.start.jhipster.sample.service.dto.ProveedorDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Proveedor.
 */
@RestController
@RequestMapping("/api")
public class ProveedorResource {

    private final Logger log = LoggerFactory.getLogger(ProveedorResource.class);

    private static final String ENTITY_NAME = "proveedor";

    private final ProveedorService proveedorService;

    public ProveedorResource(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    /**
     * POST  /proveedors : Create a new proveedor.
     *
     * @param proveedorDTO the proveedorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proveedorDTO, or with status 400 (Bad Request) if the proveedor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/proveedors")
    @Timed
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) throws URISyntaxException {
        log.debug("REST request to save Proveedor : {}", proveedorDTO);
        if (proveedorDTO.getId() != null) {
            throw new BadRequestAlertException("A new proveedor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProveedorDTO result = proveedorService.save(proveedorDTO);
        return ResponseEntity.created(new URI("/api/proveedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proveedors : Updates an existing proveedor.
     *
     * @param proveedorDTO the proveedorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proveedorDTO,
     * or with status 400 (Bad Request) if the proveedorDTO is not valid,
     * or with status 500 (Internal Server Error) if the proveedorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/proveedors")
    @Timed
    public ResponseEntity<ProveedorDTO> updateProveedor(@RequestBody ProveedorDTO proveedorDTO) throws URISyntaxException {
        log.debug("REST request to update Proveedor : {}", proveedorDTO);
        if (proveedorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProveedorDTO result = proveedorService.save(proveedorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proveedorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proveedors : get all the proveedors.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of proveedors in body
     */
    @GetMapping("/proveedors")
    @Timed
    public ResponseEntity<List<ProveedorDTO>> getAllProveedors(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Proveedors");
        Page<ProveedorDTO> page;
        if (eagerload) {
            page = proveedorService.findAllWithEagerRelationships(pageable);
        } else {
            page = proveedorService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/proveedors?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /proveedors/:id : get the "id" proveedor.
     *
     * @param id the id of the proveedorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proveedorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/proveedors/{id}")
    @Timed
    public ResponseEntity<ProveedorDTO> getProveedor(@PathVariable Long id) {
        log.debug("REST request to get Proveedor : {}", id);
        Optional<ProveedorDTO> proveedorDTO = proveedorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proveedorDTO);
    }

    /**
     * DELETE  /proveedors/:id : delete the "id" proveedor.
     *
     * @param id the id of the proveedorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/proveedors/{id}")
    @Timed
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        log.debug("REST request to delete Proveedor : {}", id);
        proveedorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
