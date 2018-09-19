package gps.mx.poc.start.jhipster.sample.web.rest;

import gps.mx.poc.start.jhipster.sample.JhipsterSampleApplicationApp;

import gps.mx.poc.start.jhipster.sample.domain.Adscripcion;
import gps.mx.poc.start.jhipster.sample.repository.AdscripcionRepository;
import gps.mx.poc.start.jhipster.sample.service.AdscripcionService;
import gps.mx.poc.start.jhipster.sample.service.dto.AdscripcionDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.AdscripcionMapper;
import gps.mx.poc.start.jhipster.sample.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static gps.mx.poc.start.jhipster.sample.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AdscripcionResource REST controller.
 *
 * @see AdscripcionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AdscripcionResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE = "BBBBBBBBBB";

    @Autowired
    private AdscripcionRepository adscripcionRepository;

    @Mock
    private AdscripcionRepository adscripcionRepositoryMock;

    @Autowired
    private AdscripcionMapper adscripcionMapper;
    

    @Mock
    private AdscripcionService adscripcionServiceMock;

    @Autowired
    private AdscripcionService adscripcionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdscripcionMockMvc;

    private Adscripcion adscripcion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdscripcionResource adscripcionResource = new AdscripcionResource(adscripcionService);
        this.restAdscripcionMockMvc = MockMvcBuilders.standaloneSetup(adscripcionResource)
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
    public static Adscripcion createEntity(EntityManager em) {
        Adscripcion adscripcion = new Adscripcion()
            .nombre(DEFAULT_NOMBRE)
            .clave(DEFAULT_CLAVE);
        return adscripcion;
    }

    @Before
    public void initTest() {
        adscripcion = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdscripcion() throws Exception {
        int databaseSizeBeforeCreate = adscripcionRepository.findAll().size();

        // Create the Adscripcion
        AdscripcionDTO adscripcionDTO = adscripcionMapper.toDto(adscripcion);
        restAdscripcionMockMvc.perform(post("/api/adscripcions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionDTO)))
            .andExpect(status().isCreated());

        // Validate the Adscripcion in the database
        List<Adscripcion> adscripcionList = adscripcionRepository.findAll();
        assertThat(adscripcionList).hasSize(databaseSizeBeforeCreate + 1);
        Adscripcion testAdscripcion = adscripcionList.get(adscripcionList.size() - 1);
        assertThat(testAdscripcion.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAdscripcion.getClave()).isEqualTo(DEFAULT_CLAVE);
    }

    @Test
    @Transactional
    public void createAdscripcionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adscripcionRepository.findAll().size();

        // Create the Adscripcion with an existing ID
        adscripcion.setId(1L);
        AdscripcionDTO adscripcionDTO = adscripcionMapper.toDto(adscripcion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdscripcionMockMvc.perform(post("/api/adscripcions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Adscripcion in the database
        List<Adscripcion> adscripcionList = adscripcionRepository.findAll();
        assertThat(adscripcionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdscripcions() throws Exception {
        // Initialize the database
        adscripcionRepository.saveAndFlush(adscripcion);

        // Get all the adscripcionList
        restAdscripcionMockMvc.perform(get("/api/adscripcions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adscripcion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].clave").value(hasItem(DEFAULT_CLAVE.toString())));
    }
    
    public void getAllAdscripcionsWithEagerRelationshipsIsEnabled() throws Exception {
        AdscripcionResource adscripcionResource = new AdscripcionResource(adscripcionServiceMock);
        when(adscripcionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAdscripcionMockMvc = MockMvcBuilders.standaloneSetup(adscripcionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAdscripcionMockMvc.perform(get("/api/adscripcions?eagerload=true"))
        .andExpect(status().isOk());

        verify(adscripcionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllAdscripcionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        AdscripcionResource adscripcionResource = new AdscripcionResource(adscripcionServiceMock);
            when(adscripcionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAdscripcionMockMvc = MockMvcBuilders.standaloneSetup(adscripcionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAdscripcionMockMvc.perform(get("/api/adscripcions?eagerload=true"))
        .andExpect(status().isOk());

            verify(adscripcionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAdscripcion() throws Exception {
        // Initialize the database
        adscripcionRepository.saveAndFlush(adscripcion);

        // Get the adscripcion
        restAdscripcionMockMvc.perform(get("/api/adscripcions/{id}", adscripcion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adscripcion.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.clave").value(DEFAULT_CLAVE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdscripcion() throws Exception {
        // Get the adscripcion
        restAdscripcionMockMvc.perform(get("/api/adscripcions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdscripcion() throws Exception {
        // Initialize the database
        adscripcionRepository.saveAndFlush(adscripcion);

        int databaseSizeBeforeUpdate = adscripcionRepository.findAll().size();

        // Update the adscripcion
        Adscripcion updatedAdscripcion = adscripcionRepository.findById(adscripcion.getId()).get();
        // Disconnect from session so that the updates on updatedAdscripcion are not directly saved in db
        em.detach(updatedAdscripcion);
        updatedAdscripcion
            .nombre(UPDATED_NOMBRE)
            .clave(UPDATED_CLAVE);
        AdscripcionDTO adscripcionDTO = adscripcionMapper.toDto(updatedAdscripcion);

        restAdscripcionMockMvc.perform(put("/api/adscripcions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionDTO)))
            .andExpect(status().isOk());

        // Validate the Adscripcion in the database
        List<Adscripcion> adscripcionList = adscripcionRepository.findAll();
        assertThat(adscripcionList).hasSize(databaseSizeBeforeUpdate);
        Adscripcion testAdscripcion = adscripcionList.get(adscripcionList.size() - 1);
        assertThat(testAdscripcion.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAdscripcion.getClave()).isEqualTo(UPDATED_CLAVE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdscripcion() throws Exception {
        int databaseSizeBeforeUpdate = adscripcionRepository.findAll().size();

        // Create the Adscripcion
        AdscripcionDTO adscripcionDTO = adscripcionMapper.toDto(adscripcion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdscripcionMockMvc.perform(put("/api/adscripcions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adscripcionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Adscripcion in the database
        List<Adscripcion> adscripcionList = adscripcionRepository.findAll();
        assertThat(adscripcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdscripcion() throws Exception {
        // Initialize the database
        adscripcionRepository.saveAndFlush(adscripcion);

        int databaseSizeBeforeDelete = adscripcionRepository.findAll().size();

        // Get the adscripcion
        restAdscripcionMockMvc.perform(delete("/api/adscripcions/{id}", adscripcion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Adscripcion> adscripcionList = adscripcionRepository.findAll();
        assertThat(adscripcionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adscripcion.class);
        Adscripcion adscripcion1 = new Adscripcion();
        adscripcion1.setId(1L);
        Adscripcion adscripcion2 = new Adscripcion();
        adscripcion2.setId(adscripcion1.getId());
        assertThat(adscripcion1).isEqualTo(adscripcion2);
        adscripcion2.setId(2L);
        assertThat(adscripcion1).isNotEqualTo(adscripcion2);
        adscripcion1.setId(null);
        assertThat(adscripcion1).isNotEqualTo(adscripcion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdscripcionDTO.class);
        AdscripcionDTO adscripcionDTO1 = new AdscripcionDTO();
        adscripcionDTO1.setId(1L);
        AdscripcionDTO adscripcionDTO2 = new AdscripcionDTO();
        assertThat(adscripcionDTO1).isNotEqualTo(adscripcionDTO2);
        adscripcionDTO2.setId(adscripcionDTO1.getId());
        assertThat(adscripcionDTO1).isEqualTo(adscripcionDTO2);
        adscripcionDTO2.setId(2L);
        assertThat(adscripcionDTO1).isNotEqualTo(adscripcionDTO2);
        adscripcionDTO1.setId(null);
        assertThat(adscripcionDTO1).isNotEqualTo(adscripcionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adscripcionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adscripcionMapper.fromId(null)).isNull();
    }
}
