package com.ivision.app.web.rest;

import com.ivision.app.IvisionApp;
import com.ivision.app.domain.Ivision;
import com.ivision.app.repository.IvisionRepository;
import com.ivision.app.service.IvisionService;
import com.ivision.app.web.rest.errors.ExceptionTranslator;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.ivision.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link IvisionResource} REST controller.
 */
@SpringBootTest(classes = IvisionApp.class)
public class IvisionResourceIT {

    private static final BigDecimal DEFAULT_PROFILE_PHOTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROFILE_PHOTO = new BigDecimal(2);
    private static final BigDecimal SMALLER_PROFILE_PHOTO = new BigDecimal(1 - 1);

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDAY = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BIRTHDAY = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_TELPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MAILBOX = "AAAAAAAAAA";
    private static final String UPDATED_MAILBOX = "BBBBBBBBBB";

    @Autowired
    private IvisionRepository ivisionRepository;

    @Autowired
    private IvisionService ivisionService;

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
        final IvisionResource ivisionResource = new IvisionResource(ivisionService);
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
            .profile_photo(DEFAULT_PROFILE_PHOTO)
            .nickname(DEFAULT_NICKNAME)
            .gender(DEFAULT_GENDER)
            .birthday(DEFAULT_BIRTHDAY)
            .telphone(DEFAULT_TELPHONE)
            .mailbox(DEFAULT_MAILBOX);
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
            .profile_photo(UPDATED_PROFILE_PHOTO)
            .nickname(UPDATED_NICKNAME)
            .gender(UPDATED_GENDER)
            .birthday(UPDATED_BIRTHDAY)
            .telphone(UPDATED_TELPHONE)
            .mailbox(UPDATED_MAILBOX);
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
        assertThat(testIvision.getProfile_photo()).isEqualTo(DEFAULT_PROFILE_PHOTO);
        assertThat(testIvision.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testIvision.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testIvision.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testIvision.getTelphone()).isEqualTo(DEFAULT_TELPHONE);
        assertThat(testIvision.getMailbox()).isEqualTo(DEFAULT_MAILBOX);
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
    public void getAllIvisions() throws Exception {
        // Initialize the database
        ivisionRepository.saveAndFlush(ivision);

        // Get all the ivisionList
        restIvisionMockMvc.perform(get("/api/ivisions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ivision.getId().intValue())))
            .andExpect(jsonPath("$.[*].profile_photo").value(hasItem(DEFAULT_PROFILE_PHOTO.intValue())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].telphone").value(hasItem(DEFAULT_TELPHONE.toString())))
            .andExpect(jsonPath("$.[*].mailbox").value(hasItem(DEFAULT_MAILBOX.toString())));
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
            .andExpect(jsonPath("$.profile_photo").value(DEFAULT_PROFILE_PHOTO.intValue()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.telphone").value(DEFAULT_TELPHONE.toString()))
            .andExpect(jsonPath("$.mailbox").value(DEFAULT_MAILBOX.toString()));
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
        ivisionService.save(ivision);

        int databaseSizeBeforeUpdate = ivisionRepository.findAll().size();

        // Update the ivision
        Ivision updatedIvision = ivisionRepository.findById(ivision.getId()).get();
        // Disconnect from session so that the updates on updatedIvision are not directly saved in db
        em.detach(updatedIvision);
        updatedIvision
            .profile_photo(UPDATED_PROFILE_PHOTO)
            .nickname(UPDATED_NICKNAME)
            .gender(UPDATED_GENDER)
            .birthday(UPDATED_BIRTHDAY)
            .telphone(UPDATED_TELPHONE)
            .mailbox(UPDATED_MAILBOX);

        restIvisionMockMvc.perform(put("/api/ivisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIvision)))
            .andExpect(status().isOk());

        // Validate the Ivision in the database
        List<Ivision> ivisionList = ivisionRepository.findAll();
        assertThat(ivisionList).hasSize(databaseSizeBeforeUpdate);
        Ivision testIvision = ivisionList.get(ivisionList.size() - 1);
        assertThat(testIvision.getProfile_photo()).isEqualTo(UPDATED_PROFILE_PHOTO);
        assertThat(testIvision.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testIvision.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testIvision.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testIvision.getTelphone()).isEqualTo(UPDATED_TELPHONE);
        assertThat(testIvision.getMailbox()).isEqualTo(UPDATED_MAILBOX);
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
        ivisionService.save(ivision);

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
