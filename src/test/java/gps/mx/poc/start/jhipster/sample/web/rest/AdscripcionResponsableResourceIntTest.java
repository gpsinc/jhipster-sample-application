package gps.mx.poc.start.jhipster.sample.web.rest;

import gps.mx.poc.start.jhipster.sample.JhipsterSampleApplicationApp;

import gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable;
import gps.mx.poc.start.jhipster.sample.repository.AdscripcionResponsableRepository;
import gps.mx.poc.start.jhipster.sample.service.AdscripcionResponsableService;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionResponsableDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.AdscripcionResponsableMapper;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static gps.mx.poc.start.jhipster.sample.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AdscripcionResponsableResource REST controller.
 *
 * @see AdscripcionResponsableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AdscripcionResponsableResourceIntTest {

    @Autowired
    private AdscripcionResponsableRepository adscripcionResponsableRepository;

    @Autowired
    private AdscripcionResponsableMapper adscripcionResponsableMapper;
    
    @Autowired
    private AdscripcionResponsableService adscripcionResponsableService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdscripcionResponsableMockMvc;

    private AdscripcionResponsable adscripcionResponsable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdscripcionResponsableResource adscripcionResponsableResource = new AdscripcionResponsableResource(adscripcionResponsableService);
        this.restAdscripcionResponsableMockMvc = MockMvcBuilders.standaloneSetup(adscripcionResponsableResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdscripcionResponsable createEntity(EntityManager em) {
        AdscripcionResponsable adscripcionResponsable = new AdscripcionResponsable();
        return adscripcionResponsable;
    }

    @Before
    public void initTest() {
        adscripcionResponsable = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdscripcionResponsable() throws Exception {
        int databaseSizeBeforeCreate = adscripcionResponsableRepository.findAll().size();

        // Create the AdscripcionResponsable
        AdscripcionResponsableDTO adscripcionResponsableDTO = adscripcionResponsableMapper.toDto(adscripcionResponsable);
        restAdscripcionResponsableMockMvc.perform(post("/api/adscripcion-responsables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionResponsableDTO)))
            .andExpect(status().isCreated());

        // Validate the AdscripcionResponsable in the database
        List<AdscripcionResponsable> adscripcionResponsableList = adscripcionResponsableRepository.findAll();
        assertThat(adscripcionResponsableList).hasSize(databaseSizeBeforeCreate + 1);
        AdscripcionResponsable testAdscripcionResponsable = adscripcionResponsableList.get(adscripcionResponsableList.size() - 1);
    }

    @Test
    @Transactional
    public void createAdscripcionResponsableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adscripcionResponsableRepository.findAll().size();

        // Create the AdscripcionResponsable with an existing ID
        adscripcionResponsable.setId(1L);
        AdscripcionResponsableDTO adscripcionResponsableDTO = adscripcionResponsableMapper.toDto(adscripcionResponsable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdscripcionResponsableMockMvc.perform(post("/api/adscripcion-responsables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionResponsableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdscripcionResponsable in the database
        List<AdscripcionResponsable> adscripcionResponsableList = adscripcionResponsableRepository.findAll();
        assertThat(adscripcionResponsableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdscripcionResponsables() throws Exception {
        // Initialize the database
        adscripcionResponsableRepository.saveAndFlush(adscripcionResponsable);

        // Get all the adscripcionResponsableList
        restAdscripcionResponsableMockMvc.perform(get("/api/adscripcion-responsables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adscripcionResponsable.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAdscripcionResponsable() throws Exception {
        // Initialize the database
        adscripcionResponsableRepository.saveAndFlush(adscripcionResponsable);

        // Get the adscripcionResponsable
        restAdscripcionResponsableMockMvc.perform(get("/api/adscripcion-responsables/{id}", adscripcionResponsable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adscripcionResponsable.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAdscripcionResponsable() throws Exception {
        // Get the adscripcionResponsable
        restAdscripcionResponsableMockMvc.perform(get("/api/adscripcion-responsables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdscripcionResponsable() throws Exception {
        // Initialize the database
        adscripcionResponsableRepository.saveAndFlush(adscripcionResponsable);

        int databaseSizeBeforeUpdate = adscripcionResponsableRepository.findAll().size();

        // Update the adscripcionResponsable
        AdscripcionResponsable updatedAdscripcionResponsable = adscripcionResponsableRepository.findById(adscripcionResponsable.getId()).get();
        // Disconnect from session so that the updates on updatedAdscripcionResponsable are not directly saved in db
        em.detach(updatedAdscripcionResponsable);
        AdscripcionResponsableDTO adscripcionResponsableDTO = adscripcionResponsableMapper.toDto(updatedAdscripcionResponsable);

        restAdscripcionResponsableMockMvc.perform(put("/api/adscripcion-responsables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionResponsableDTO)))
            .andExpect(status().isOk());

        // Validate the AdscripcionResponsable in the database
        List<AdscripcionResponsable> adscripcionResponsableList = adscripcionResponsableRepository.findAll();
        assertThat(adscripcionResponsableList).hasSize(databaseSizeBeforeUpdate);
        AdscripcionResponsable testAdscripcionResponsable = adscripcionResponsableList.get(adscripcionResponsableList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAdscripcionResponsable() throws Exception {
        int databaseSizeBeforeUpdate = adscripcionResponsableRepository.findAll().size();

        // Create the AdscripcionResponsable
        AdscripcionResponsableDTO adscripcionResponsableDTO = adscripcionResponsableMapper.toDto(adscripcionResponsable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdscripcionResponsableMockMvc.perform(put("/api/adscripcion-responsables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionResponsableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdscripcionResponsable in the database
        List<AdscripcionResponsable> adscripcionResponsableList = adscripcionResponsableRepository.findAll();
        assertThat(adscripcionResponsableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdscripcionResponsable() throws Exception {
        // Initialize the database
        adscripcionResponsableRepository.saveAndFlush(adscripcionResponsable);

        int databaseSizeBeforeDelete = adscripcionResponsableRepository.findAll().size();

        // Get the adscripcionResponsable
        restAdscripcionResponsableMockMvc.perform(delete("/api/adscripcion-responsables/{id}", adscripcionResponsable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdscripcionResponsable> adscripcionResponsableList = adscripcionResponsableRepository.findAll();
        assertThat(adscripcionResponsableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdscripcionResponsable.class);
        AdscripcionResponsable adscripcionResponsable1 = new AdscripcionResponsable();
        adscripcionResponsable1.setId(1L);
        AdscripcionResponsable adscripcionResponsable2 = new AdscripcionResponsable();
        adscripcionResponsable2.setId(adscripcionResponsable1.getId());
        assertThat(adscripcionResponsable1).isEqualTo(adscripcionResponsable2);
        adscripcionResponsable2.setId(2L);
        assertThat(adscripcionResponsable1).isNotEqualTo(adscripcionResponsable2);
        adscripcionResponsable1.setId(null);
        assertThat(adscripcionResponsable1).isNotEqualTo(adscripcionResponsable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdscripcionResponsableDTO.class);
        AdscripcionResponsableDTO adscripcionResponsableDTO1 = new AdscripcionResponsableDTO();
        adscripcionResponsableDTO1.setId(1L);
        AdscripcionResponsableDTO adscripcionResponsableDTO2 = new AdscripcionResponsableDTO();
        assertThat(adscripcionResponsableDTO1).isNotEqualTo(adscripcionResponsableDTO2);
        adscripcionResponsableDTO2.setId(adscripcionResponsableDTO1.getId());
        assertThat(adscripcionResponsableDTO1).isEqualTo(adscripcionResponsableDTO2);
        adscripcionResponsableDTO2.setId(2L);
        assertThat(adscripcionResponsableDTO1).isNotEqualTo(adscripcionResponsableDTO2);
        adscripcionResponsableDTO1.setId(null);
        assertThat(adscripcionResponsableDTO1).isNotEqualTo(adscripcionResponsableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adscripcionResponsableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adscripcionResponsableMapper.fromId(null)).isNull();
    }
}
