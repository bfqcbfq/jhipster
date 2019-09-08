package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Ivision;
import com.mycompany.myapp.repository.IvisionRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link IvisionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
public class IvisionResourceIT {

    private static final String DEFAULT_CUST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_CUST_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_CUST_INDUSTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_CUST_LEVEL = "BBBBBBBBBB";

    @Autowired
    private IvisionRepository ivisionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restIvisionMockMvc;

    private Ivision ivision;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IvisionResource ivisionResource = new IvisionResource(ivisionRepository);
        this.restIvisionMockMvc = MockMvcBuilders.standaloneSetup(ivisionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ivision createEntity(EntityManager em) {
        Ivision ivision = new Ivision()
            .cust_name(DEFAULT_CUST_NAME)
            .cust_source(DEFAULT_CUST_SOURCE)
            .cust_industry(DEFAULT_CUST_INDUSTRY)
            .cust_level(DEFAULT_CUST_LEVEL);
        return ivision;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ivision createUpdatedEntity(EntityManager em) {
        Ivision ivision = new Ivision()
            .cust_name(UPDATED_CUST_NAME)
            .cust_source(UPDATED_CUST_SOURCE)
            .cust_industry(UPDATED_CUST_INDUSTRY)
            .cust_level(UPDATED_CUST_LEVEL);
        return ivision;
    }

    @BeforeEach
    public void initTest() {
        ivision = createEntity(em);
    }

    @Test
    @Transactional
    public void createIvision() throws Exception {
        int databaseSizeBeforeCreate = ivisionRepository.findAll().size();

        // Create the Ivision
        restIvisionMockMvc.perform(post("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ivision)))
            .andExpect(status().isCreated());

        // Validate the Ivision in the database
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeCreate + 1);
        Ivision testIvision = ivisionList.get(ivisionList.size() - 1);
        assertThat(testIvision.getCust_name()).isEqualTo(DEFAULT_CUST_NAME);
        assertThat(testIvision.getCust_source()).isEqualTo(DEFAULT_CUST_SOURCE);
        assertThat(testIvision.getCust_industry()).isEqualTo(DEFAULT_CUST_INDUSTRY);
        assertThat(testIvision.getCust_level()).isEqualTo(DEFAULT_CUST_LEVEL);
    }

    @Test
    @Transactional
    public void createIvisionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ivisionRepository.findAll().size();

        // Create the Ivision with an existing ID
        ivision.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIvisionMockMvc.perform(post("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ivision)))
            .andExpect(status().isBadRequest());

        // Validate the Ivision in the database
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCust_nameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ivisionRepository.findAll().size();
        // set the field null
        ivision.setCust_name(null);

        // Create the Ivision, which fails.

        restIvisionMockMvc.perform(post("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ivision)))
            .andExpect(status().isBadRequest());

        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIvisions() throws Exception {
        // Initialize the database
        ivisionRepository.saveAndFlush(ivision);

        // Get all the ivisionList
        restIvisionMockMvc.perform(get("/api/ivisions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ivision.getId().intValue())))
            .andExpect(jsonPath("$.[*].cust_name").value(hasItem(DEFAULT_CUST_NAME.toString())))
            .andExpect(jsonPath("$.[*].cust_source").value(hasItem(DEFAULT_CUST_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].cust_industry").value(hasItem(DEFAULT_CUST_INDUSTRY.toString())))
            .andExpect(jsonPath("$.[*].cust_level").value(hasItem(DEFAULT_CUST_LEVEL.toString())));
    }
    
    @Test
    @Transactional
    public void getIvision() throws Exception {
        // Initialize the database
        ivisionRepository.saveAndFlush(ivision);

        // Get the ivision
        restIvisionMockMvc.perform(get("/api/ivisions/{id}", ivision.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ivision.getId().intValue()))
            .andExpect(jsonPath("$.cust_name").value(DEFAULT_CUST_NAME.toString()))
            .andExpect(jsonPath("$.cust_source").value(DEFAULT_CUST_SOURCE.toString()))
            .andExpect(jsonPath("$.cust_industry").value(DEFAULT_CUST_INDUSTRY.toString()))
            .andExpect(jsonPath("$.cust_level").value(DEFAULT_CUST_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIvision() throws Exception {
        // Get the ivision
        restIvisionMockMvc.perform(get("/api/ivisions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIvision() throws Exception {
        // Initialize the database
        ivisionRepository.saveAndFlush(ivision);

        int databaseSizeBeforeUpdate = ivisionRepository.findAll().size();

        // Update the ivision
        Ivision updatedIvision = ivisionRepository.findById(ivision.getId()).get();
        // Disconnect from session so that the updates on updatedIvision are not directly saved in db
        em.detach(updatedIvision);
        updatedIvision
            .cust_name(UPDATED_CUST_NAME)
            .cust_source(UPDATED_CUST_SOURCE)
            .cust_industry(UPDATED_CUST_INDUSTRY)
            .cust_level(UPDATED_CUST_LEVEL);

        restIvisionMockMvc.perform(put("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIvision)))
            .andExpect(status().isOk());

        // Validate the Ivision in the database
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeUpdate);
        Ivision testIvision = ivisionList.get(ivisionList.size() - 1);
        assertThat(testIvision.getCust_name()).isEqualTo(UPDATED_CUST_NAME);
        assertThat(testIvision.getCust_source()).isEqualTo(UPDATED_CUST_SOURCE);
        assertThat(testIvision.getCust_industry()).isEqualTo(UPDATED_CUST_INDUSTRY);
        assertThat(testIvision.getCust_level()).isEqualTo(UPDATED_CUST_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingIvision() throws Exception {
        int databaseSizeBeforeUpdate = ivisionRepository.findAll().size();

        // Create the Ivision

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIvisionMockMvc.perform(put("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ivision)))
            .andExpect(status().isBadRequest());

        // Validate the Ivision in the database
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIvision() throws Exception {
        // Initialize the database
        ivisionRepository.saveAndFlush(ivision);

        int databaseSizeBeforeDelete = ivisionRepository.findAll().size();

        // Delete the ivision
        restIvisionMockMvc.perform(delete("/api/ivisions/{id}", ivision.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ivision.class);
        Ivision ivision1 = new Ivision();
        ivision1.setId(1L);
        Ivision ivision2 = new Ivision();
        ivision2.setId(ivision1.getId());
        assertThat(ivision1).isEqualTo(ivision2);
        ivision2.setId(2L);
        assertThat(ivision1).isNotEqualTo(ivision2);
        ivision1.setId(null);
        assertThat(ivision1).isNotEqualTo(ivision2);
    }
}
