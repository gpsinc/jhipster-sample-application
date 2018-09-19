package gps.mx.poc.start.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import gps.mx.poc.start.jhipster.sample.service.AdscripcionResponsableService;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.BadRequestAlertException;
import gps.mx.poc.start.jhipster.sample.web.rest.util.HeaderUtil;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionResponsableDTO;
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
 * REST controller for managing AdscripcionResponsable.
 */
@RestController
@RequestMapping("/api")
public class AdscripcionResponsableResource {

    private final Logger log = LoggerFactory.getLogger(AdscripcionResponsableResource.class);

    private static final String ENTITY_NAME = "adscripcionResponsable";

    private final AdscripcionResponsableService adscripcionResponsableService;

    public AdscripcionResponsableResource(AdscripcionResponsableService adscripcionResponsableService) {
        this.adscripcionResponsableService = adscripcionResponsableService;
    }

    /**
     * POST  /adscripcion-responsables : Create a new adscripcionResponsable.
     *
     * @param adscripcionResponsableDTO the adscripcionResponsableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adscripcionResponsableDTO, or with status 400 (Bad Request) if the adscripcionResponsable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adscripcion-responsables")
    @Timed
    public ResponseEntity<AdscripcionResponsableDTO> createAdscripcionResponsable(@RequestBody AdscripcionResponsableDTO adscripcionResponsableDTO) throws URISyntaxException {
        log.debug("REST request to save AdscripcionResponsable : {}", adscripcionResponsableDTO);
        if (adscripcionResponsableDTO.getId() != null) {
            throw new BadRequestAlertException("A new adscripcionResponsable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdscripcionResponsableDTO result = adscripcionResponsableService.save(adscripcionResponsableDTO);
        return ResponseEntity.created(new URI("/api/adscripcion-responsables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adscripcion-responsables : Updates an existing adscripcionResponsable.
     *
     * @param adscripcionResponsableDTO the adscripcionResponsableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adscripcionResponsableDTO,
     * or with status 400 (Bad Request) if the adscripcionResponsableDTO is not valid,
     * or with status 500 (Internal Server Error) if the adscripcionResponsableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adscripcion-responsables")
    @Timed
    public ResponseEntity<AdscripcionResponsableDTO> updateAdscripcionResponsable(@RequestBody AdscripcionResponsableDTO adscripcionResponsableDTO) throws URISyntaxException {
        log.debug("REST request to update AdscripcionResponsable : {}", adscripcionResponsableDTO);
        if (adscripcionResponsableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdscripcionResponsableDTO result = adscripcionResponsableService.save(adscripcionResponsableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adscripcionResponsableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adscripcion-responsables : get all the adscripcionResponsables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adscripcionResponsables in body
     */
    @GetMapping("/adscripcion-responsables")
    @Timed
    public List<AdscripcionResponsableDTO> getAllAdscripcionResponsables() {
        log.debug("REST request to get all AdscripcionResponsables");
        return adscripcionResponsableService.findAll();
    }

    /**
     * GET  /adscripcion-responsables/:id : get the "id" adscripcionResponsable.
     *
     * @param id the id of the adscripcionResponsableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adscripcionResponsableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adscripcion-responsables/{id}")
    @Timed
    public ResponseEntity<AdscripcionResponsableDTO> getAdscripcionResponsable(@PathVariable Long id) {
        log.debug("REST request to get AdscripcionResponsable : {}", id);
        Optional<AdscripcionResponsableDTO> adscripcionResponsableDTO = adscripcionResponsableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adscripcionResponsableDTO);
    }

    /**
     * DELETE  /adscripcion-responsables/:id : delete the "id" adscripcionResponsable.
     *
     * @param id the id of the adscripcionResponsableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adscripcion-responsables/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdscripcionResponsable(@PathVariable Long id) {
        log.debug("REST request to delete AdscripcionResponsable : {}", id);
        adscripcionResponsableService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
