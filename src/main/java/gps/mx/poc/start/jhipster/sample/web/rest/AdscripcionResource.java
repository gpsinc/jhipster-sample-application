package gps.mx.poc.start.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import gps.mx.poc.start.jhipster.sample.service.AdscripcionService;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.BadRequestAlertException;
import gps.mx.poc.start.jhipster.sample.web.rest.util.HeaderUtil;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionDTO;
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
 * REST controller for managing Adscripcion.
 */
@RestController
@RequestMapping("/api")
public class AdscripcionResource {

    private final Logger log = LoggerFactory.getLogger(AdscripcionResource.class);

    private static final String ENTITY_NAME = "adscripcion";

    private final AdscripcionService adscripcionService;

    public AdscripcionResource(AdscripcionService adscripcionService) {
        this.adscripcionService = adscripcionService;
    }

    /**
     * POST  /adscripcions : Create a new adscripcion.
     *
     * @param adscripcionDTO the adscripcionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adscripcionDTO, or with status 400 (Bad Request) if the adscripcion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adscripcions")
    @Timed
    public ResponseEntity<AdscripcionDTO> createAdscripcion(@RequestBody AdscripcionDTO adscripcionDTO) throws URISyntaxException {
        log.debug("REST request to save Adscripcion : {}", adscripcionDTO);
        if (adscripcionDTO.getId() != null) {
            throw new BadRequestAlertException("A new adscripcion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdscripcionDTO result = adscripcionService.save(adscripcionDTO);
        return ResponseEntity.created(new URI("/api/adscripcions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adscripcions : Updates an existing adscripcion.
     *
     * @param adscripcionDTO the adscripcionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adscripcionDTO,
     * or with status 400 (Bad Request) if the adscripcionDTO is not valid,
     * or with status 500 (Internal Server Error) if the adscripcionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adscripcions")
    @Timed
    public ResponseEntity<AdscripcionDTO> updateAdscripcion(@RequestBody AdscripcionDTO adscripcionDTO) throws URISyntaxException {
        log.debug("REST request to update Adscripcion : {}", adscripcionDTO);
        if (adscripcionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdscripcionDTO result = adscripcionService.save(adscripcionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adscripcionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adscripcions : get all the adscripcions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of adscripcions in body
     */
    @GetMapping("/adscripcions")
    @Timed
    public List<AdscripcionDTO> getAllAdscripcions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Adscripcions");
        return adscripcionService.findAll();
    }

    /**
     * GET  /adscripcions/:id : get the "id" adscripcion.
     *
     * @param id the id of the adscripcionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adscripcionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adscripcions/{id}")
    @Timed
    public ResponseEntity<AdscripcionDTO> getAdscripcion(@PathVariable Long id) {
        log.debug("REST request to get Adscripcion : {}", id);
        Optional<AdscripcionDTO> adscripcionDTO = adscripcionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adscripcionDTO);
    }

    /**
     * DELETE  /adscripcions/:id : delete the "id" adscripcion.
     *
     * @param id the id of the adscripcionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adscripcions/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdscripcion(@PathVariable Long id) {
        log.debug("REST request to delete Adscripcion : {}", id);
        adscripcionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
