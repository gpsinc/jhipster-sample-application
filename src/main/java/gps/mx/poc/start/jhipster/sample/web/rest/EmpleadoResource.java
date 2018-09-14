package gps.mx.poc.start.jhipster.sample.web.rest;

import com.codahale.metrics.annotation.Timed;
import gps.mx.poc.start.jhipster.sample.service.EmpleadoService;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.BadRequestAlertException;
import gps.mx.poc.start.jhipster.sample.web.rest.util.HeaderUtil;
import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;
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
 * REST controller for managing Empleado.
 */
@RestController
@RequestMapping("/api")
public class EmpleadoResource {

    private final Logger log = LoggerFactory.getLogger(EmpleadoResource.class);

    private static final String ENTITY_NAME = "empleado";

    private final EmpleadoService empleadoService;

    public EmpleadoResource(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * POST  /empleados : Create a new empleado.
     *
     * @param empleadoDTO the empleadoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new empleadoDTO, or with status 400 (Bad Request) if the empleado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/empleados")
    @Timed
    public ResponseEntity<EmpleadoDTO> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        log.debug("REST request to save Empleado : {}", empleadoDTO);
        if (empleadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new empleado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmpleadoDTO result = empleadoService.save(empleadoDTO);
        return ResponseEntity.created(new URI("/api/empleados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empleados : Updates an existing empleado.
     *
     * @param empleadoDTO the empleadoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated empleadoDTO,
     * or with status 400 (Bad Request) if the empleadoDTO is not valid,
     * or with status 500 (Internal Server Error) if the empleadoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/empleados")
    @Timed
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        log.debug("REST request to update Empleado : {}", empleadoDTO);
        if (empleadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmpleadoDTO result = empleadoService.save(empleadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, empleadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empleados : get all the empleados.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of empleados in body
     */
    @GetMapping("/empleados")
    @Timed
    public List<EmpleadoDTO> getAllEmpleados(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Empleados");
        return empleadoService.findAll();
    }

    /**
     * GET  /empleados/:id : get the "id" empleado.
     *
     * @param id the id of the empleadoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the empleadoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/empleados/{id}")
    @Timed
    public ResponseEntity<EmpleadoDTO> getEmpleado(@PathVariable Long id) {
        log.debug("REST request to get Empleado : {}", id);
        Optional<EmpleadoDTO> empleadoDTO = empleadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(empleadoDTO);
    }

    /**
     * DELETE  /empleados/:id : delete the "id" empleado.
     *
     * @param id the id of the empleadoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/empleados/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        log.debug("REST request to delete Empleado : {}", id);
        empleadoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
