package gps.mx.poc.start.jhipster.sample.web.rest;

import gps.mx.poc.start.jhipster.sample.JhipsterSampleApplicationApp;

import gps.mx.poc.start.jhipster.sample.domain.Empleado;
import gps.mx.poc.start.jhipster.sample.repository.EmpleadoRepository;
import gps.mx.poc.start.jhipster.sample.service.EmpleadoService;
import gps.mx.poc.start.jhipster.sample.service.dto.EmpleadoDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.EmpleadoMapper;
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
 * Test class for the EmpleadoResource REST controller.
 *
 * @see EmpleadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class EmpleadoResourceIntTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Mock
    private EmpleadoRepository empleadoRepositoryMock;

    @Autowired
    private EmpleadoMapper empleadoMapper;
    

    @Mock
    private EmpleadoService empleadoServiceMock;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmpleadoMockMvc;

    private Empleado empleado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmpleadoResource empleadoResource = new EmpleadoResource(empleadoService);
        this.restEmpleadoMockMvc = MockMvcBuilders.standaloneSetup(empleadoResource)
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
    public static Empleado createEntity(EntityManager em) {
        Empleado empleado = new Empleado();
        return empleado;
    }

    @Before
    public void initTest() {
        empleado = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpleado() throws Exception {
        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();

        // Create the Empleado
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleado);
        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleadoDTO)))
            .andExpect(status().isCreated());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate + 1);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
    }

    @Test
    @Transactional
    public void createEmpleadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();

        // Create the Empleado with an existing ID
        empleado.setId(1L);
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmpleados() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        // Get all the empleadoList
        restEmpleadoMockMvc.perform(get("/api/empleados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleado.getId().intValue())));
    }
    
    public void getAllEmpleadosWithEagerRelationshipsIsEnabled() throws Exception {
        EmpleadoResource empleadoResource = new EmpleadoResource(empleadoServiceMock);
        when(empleadoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restEmpleadoMockMvc = MockMvcBuilders.standaloneSetup(empleadoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEmpleadoMockMvc.perform(get("/api/empleados?eagerload=true"))
        .andExpect(status().isOk());

        verify(empleadoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllEmpleadosWithEagerRelationshipsIsNotEnabled() throws Exception {
        EmpleadoResource empleadoResource = new EmpleadoResource(empleadoServiceMock);
            when(empleadoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restEmpleadoMockMvc = MockMvcBuilders.standaloneSetup(empleadoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restEmpleadoMockMvc.perform(get("/api/empleados?eagerload=true"))
        .andExpect(status().isOk());

            verify(empleadoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        // Get the empleado
        restEmpleadoMockMvc.perform(get("/api/empleados/{id}", empleado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empleado.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpleado() throws Exception {
        // Get the empleado
        restEmpleadoMockMvc.perform(get("/api/empleados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Update the empleado
        Empleado updatedEmpleado = empleadoRepository.findById(empleado.getId()).get();
        // Disconnect from session so that the updates on updatedEmpleado are not directly saved in db
        em.detach(updatedEmpleado);
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(updatedEmpleado);

        restEmpleadoMockMvc.perform(put("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleadoDTO)))
            .andExpect(status().isOk());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Create the Empleado
        EmpleadoDTO empleadoDTO = empleadoMapper.toDto(empleado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc.perform(put("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleadoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        int databaseSizeBeforeDelete = empleadoRepository.findAll().size();

        // Get the empleado
        restEmpleadoMockMvc.perform(delete("/api/empleados/{id}", empleado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empleado.class);
        Empleado empleado1 = new Empleado();
        empleado1.setId(1L);
        Empleado empleado2 = new Empleado();
        empleado2.setId(empleado1.getId());
        assertThat(empleado1).isEqualTo(empleado2);
        empleado2.setId(2L);
        assertThat(empleado1).isNotEqualTo(empleado2);
        empleado1.setId(null);
        assertThat(empleado1).isNotEqualTo(empleado2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpleadoDTO.class);
        EmpleadoDTO empleadoDTO1 = new EmpleadoDTO();
        empleadoDTO1.setId(1L);
        EmpleadoDTO empleadoDTO2 = new EmpleadoDTO();
        assertThat(empleadoDTO1).isNotEqualTo(empleadoDTO2);
        empleadoDTO2.setId(empleadoDTO1.getId());
        assertThat(empleadoDTO1).isEqualTo(empleadoDTO2);
        empleadoDTO2.setId(2L);
        assertThat(empleadoDTO1).isNotEqualTo(empleadoDTO2);
        empleadoDTO1.setId(null);
        assertThat(empleadoDTO1).isNotEqualTo(empleadoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(empleadoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(empleadoMapper.fromId(null)).isNull();
    }
}
