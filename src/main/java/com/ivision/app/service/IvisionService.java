package com.ivision.app.service;

import com.ivision.app.domain.Ivision;
import com.ivision.app.repository.IvisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ivision}.
 */
@Service
@Transactional
public class IvisionService {

    private final Logger log = LoggerFactory.getLogger(IvisionService.class);

    private final IvisionRepository ivisionRepository;

    public IvisionService(IvisionRepository ivisionRepository) {
        this.ivisionRepository = ivisionRepository;
    }

    /**
     * Save a ivision.
     *
     * @param ivision the entity to save.
     * @return the persisted entity.
     */
    public Ivision save(Ivision ivision) {
        log.debug("Request to save Ivision : {}", ivision);
        return ivisionRepository.save(ivision);
    }

    /**
     * Get all the ivisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Ivision> findAll(Pageable pageable) {
        log.debug("Request to get all Ivisions");
        return ivisionRepository.findAll(pageable);
    }


    /**
     * Get one ivision by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Ivision> findOne(Long id) {
        log.debug("Request to get Ivision : {}", id);
        return ivisionRepository.findById(id);
    }

    /**
     * Delete the ivision by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ivision : {}", id);
        ivisionRepository.deleteById(id);
    }
}
