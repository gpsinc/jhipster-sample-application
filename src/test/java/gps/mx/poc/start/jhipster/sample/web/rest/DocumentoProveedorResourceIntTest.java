package gps.mx.poc.start.jhipster.sample.web.rest;

import gps.mx.poc.start.jhipster.sample.JhipsterSampleApplicationApp;

import gps.mx.poc.start.jhipster.sample.domain.DocumentoProveedor;
import gps.mx.poc.start.jhipster.sample.repository.DocumentoProveedorRepository;
import gps.mx.poc.start.jhipster.sample.service.DocumentoProveedorService;
import gps.mx.poc.start.jhipster.sample.service.dto.DocumentoProveedorDTO;
import gps.mx.poc.start.jhipster.sample.service.mapper.DocumentoProveedorMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static gps.mx.poc.start.jhipster.sample.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DocumentoProveedorResource REST controller.
 *
 * @see DocumentoProveedorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DocumentoProveedorResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CONTENIDO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CONTENIDO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CONTENIDO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTENIDO_CONTENT_TYPE = "image/png";

    @Autowired
    private DocumentoProveedorRepository documentoProveedorRepository;

    @Autowired
    private DocumentoProveedorMapper documentoProveedorMapper;
    
    @Autowired
    private DocumentoProveedorService documentoProveedorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDocumentoProveedorMockMvc;

    private DocumentoProveedor documentoProveedor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DocumentoProveedorResource documentoProveedorResource = new DocumentoProveedorResource(documentoProveedorService);
        this.restDocumentoProveedorMockMvc = MockMvcBuilders.standaloneSetup(documentoProveedorResource)
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
    public static DocumentoProveedor createEntity(EntityManager em) {
        DocumentoProveedor documentoProveedor = new DocumentoProveedor()
            .nombre(DEFAULT_NOMBRE)
            .extension(DEFAULT_EXTENSION)
            .tipo(DEFAULT_TIPO)
            .contenido(DEFAULT_CONTENIDO)
            .contenidoContentType(DEFAULT_CONTENIDO_CONTENT_TYPE);
        return documentoProveedor;
    }

    @Before
    public void initTest() {
        documentoProveedor = createEntity(em);
    }

    @Test
    @Transactional
    public void createDocumentoProveedor() throws Exception {
        int databaseSizeBeforeCreate = documentoProveedorRepository.findAll().size();

        // Create the DocumentoProveedor
        DocumentoProveedorDTO documentoProveedorDTO = documentoProveedorMapper.toDto(documentoProveedor);
        restDocumentoProveedorMockMvc.perform(post("/api/documento-proveedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentoProveedorDTO)))
            .andExpect(status().isCreated());

        // Validate the DocumentoProveedor in the database
        List<DocumentoProveedor> documentoProveedorList = documentoProveedorRepository.findAll();
        assertThat(documentoProveedorList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentoProveedor testDocumentoProveedor = documentoProveedorList.get(documentoProveedorList.size() - 1);
        assertThat(testDocumentoProveedor.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testDocumentoProveedor.getExtension()).isEqualTo(DEFAULT_EXTENSION);
        assertThat(testDocumentoProveedor.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testDocumentoProveedor.getContenido()).isEqualTo(DEFAULT_CONTENIDO);
        assertThat(testDocumentoProveedor.getContenidoContentType()).isEqualTo(DEFAULT_CONTENIDO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createDocumentoProveedorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentoProveedorRepository.findAll().size();

        // Create the DocumentoProveedor with an existing ID
        documentoProveedor.setId(1L);
        DocumentoProveedorDTO documentoProveedorDTO = documentoProveedorMapper.toDto(documentoProveedor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentoProveedorMockMvc.perform(post("/api/documento-proveedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentoProveedorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentoProveedor in the database
        List<DocumentoProveedor> documentoProveedorList = documentoProveedorRepository.findAll();
        assertThat(documentoProveedorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDocumentoProveedors() throws Exception {
        // Initialize the database
        documentoProveedorRepository.saveAndFlush(documentoProveedor);

        // Get all the documentoProveedorList
        restDocumentoProveedorMockMvc.perform(get("/api/documento-proveedors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentoProveedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].extension").value(hasItem(DEFAULT_EXTENSION.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].contenidoContentType").value(hasItem(DEFAULT_CONTENIDO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTENIDO))));
    }
    
    @Test
    @Transactional
    public void getDocumentoProveedor() throws Exception {
        // Initialize the database
        documentoProveedorRepository.saveAndFlush(documentoProveedor);

        // Get the documentoProveedor
        restDocumentoProveedorMockMvc.perform(get("/api/documento-proveedors/{id}", documentoProveedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(documentoProveedor.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.extension").value(DEFAULT_EXTENSION.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.contenidoContentType").value(DEFAULT_CONTENIDO_CONTENT_TYPE))
            .andExpect(jsonPath("$.contenido").value(Base64Utils.encodeToString(DEFAULT_CONTENIDO)));
    }

    @Test
    @Transactional
    public void getNonExistingDocumentoProveedor() throws Exception {
        // Get the documentoProveedor
        restDocumentoProveedorMockMvc.perform(get("/api/documento-proveedors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocumentoProveedor() throws Exception {
        // Initialize the database
        documentoProveedorRepository.saveAndFlush(documentoProveedor);

        int databaseSizeBeforeUpdate = documentoProveedorRepository.findAll().size();

        // Update the documentoProveedor
        DocumentoProveedor updatedDocumentoProveedor = documentoProveedorRepository.findById(documentoProveedor.getId()).get();
        // Disconnect from session so that the updates on updatedDocumentoProveedor are not directly saved in db
        em.detach(updatedDocumentoProveedor);
        updatedDocumentoProveedor
            .nombre(UPDATED_NOMBRE)
            .extension(UPDATED_EXTENSION)
            .tipo(UPDATED_TIPO)
            .contenido(UPDATED_CONTENIDO)
            .contenidoContentType(UPDATED_CONTENIDO_CONTENT_TYPE);
        DocumentoProveedorDTO documentoProveedorDTO = documentoProveedorMapper.toDto(updatedDocumentoProveedor);

        restDocumentoProveedorMockMvc.perform(put("/api/documento-proveedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentoProveedorDTO)))
            .andExpect(status().isOk());

        // Validate the DocumentoProveedor in the database
        List<DocumentoProveedor> documentoProveedorList = documentoProveedorRepository.findAll();
        assertThat(documentoProveedorList).hasSize(databaseSizeBeforeUpdate);
        DocumentoProveedor testDocumentoProveedor = documentoProveedorList.get(documentoProveedorList.size() - 1);
        assertThat(testDocumentoProveedor.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testDocumentoProveedor.getExtension()).isEqualTo(UPDATED_EXTENSION);
        assertThat(testDocumentoProveedor.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testDocumentoProveedor.getContenido()).isEqualTo(UPDATED_CONTENIDO);
        assertThat(testDocumentoProveedor.getContenidoContentType()).isEqualTo(UPDATED_CONTENIDO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDocumentoProveedor() throws Exception {
        int databaseSizeBeforeUpdate = documentoProveedorRepository.findAll().size();

        // Create the DocumentoProveedor
        DocumentoProveedorDTO documentoProveedorDTO = documentoProveedorMapper.toDto(documentoProveedor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentoProveedorMockMvc.perform(put("/api/documento-proveedors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentoProveedorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentoProveedor in the database
        List<DocumentoProveedor> documentoProveedorList = documentoProveedorRepository.findAll();
        assertThat(documentoProveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDocumentoProveedor() throws Exception {
        // Initialize the database
        documentoProveedorRepository.saveAndFlush(documentoProveedor);

        int databaseSizeBeforeDelete = documentoProveedorRepository.findAll().size();

        // Get the documentoProveedor
        restDocumentoProveedorMockMvc.perform(delete("/api/documento-proveedors/{id}", documentoProveedor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DocumentoProveedor> documentoProveedorList = documentoProveedorRepository.findAll();
        assertThat(documentoProveedorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentoProveedor.class);
        DocumentoProveedor documentoProveedor1 = new DocumentoProveedor();
        documentoProveedor1.setId(1L);
        DocumentoProveedor documentoProveedor2 = new DocumentoProveedor();
        documentoProveedor2.setId(documentoProveedor1.getId());
        assertThat(documentoProveedor1).isEqualTo(documentoProveedor2);
        documentoProveedor2.setId(2L);
        assertThat(documentoProveedor1).isNotEqualTo(documentoProveedor2);
        documentoProveedor1.setId(null);
        assertThat(documentoProveedor1).isNotEqualTo(documentoProveedor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentoProveedorDTO.class);
        DocumentoProveedorDTO documentoProveedorDTO1 = new DocumentoProveedorDTO();
        documentoProveedorDTO1.setId(1L);
        DocumentoProveedorDTO documentoProveedorDTO2 = new DocumentoProveedorDTO();
        assertThat(documentoProveedorDTO1).isNotEqualTo(documentoProveedorDTO2);
        documentoProveedorDTO2.setId(documentoProveedorDTO1.getId());
        assertThat(documentoProveedorDTO1).isEqualTo(documentoProveedorDTO2);
        documentoProveedorDTO2.setId(2L);
        assertThat(documentoProveedorDTO1).isNotEqualTo(documentoProveedorDTO2);
        documentoProveedorDTO1.setId(null);
        assertThat(documentoProveedorDTO1).isNotEqualTo(documentoProveedorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(documentoProveedorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(documentoProveedorMapper.fromId(null)).isNull();
    }
}
