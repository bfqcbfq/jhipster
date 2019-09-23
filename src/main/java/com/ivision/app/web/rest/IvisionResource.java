package com.ivision.app.web.rest;

import com.ivision.app.domain.Ivision;
import com.ivision.app.service.IvisionService;
import com.ivision.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ivision.app.domain.Ivision}.
 */
@RestController
@RequestMapping("/api")
public class IvisionResource {

    private final Logger log = LoggerFactory.getLogger(IvisionResource.class);

    private static final String ENTITY_NAME = "ivision";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IvisionService ivisionService;

    public IvisionResource(IvisionService ivisionService) {
        this.ivisionService = ivisionService;
    }

    /**
     * {@code POST  /ivisions} : Create a new ivision.
     *
     * @param ivision the ivision to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ivision, or with status {@code 400 (Bad Request)} if the ivision has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ivisions")
    public ResponseEntity<Ivision> createIvision(@RequestBody Ivision ivision) throws URISyntaxException {
        log.debug("REST request to save Ivision : {}", ivision);
        if (ivision.getId() != null) {
            throw new BadRequestAlertException("A new ivision cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ivision result = ivisionService.save(ivision);
        return ResponseEntity.created(new URI("/api/ivisions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ivisions} : Updates an existing ivision.
     *
     * @param ivision the ivision to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ivision,
     * or with status {@code 400 (Bad Request)} if the ivision is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ivision couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ivisions")
    public ResponseEntity<Ivision> updateIvision(@RequestBody Ivision ivision) throws URISyntaxException {
        log.debug("REST request to update Ivision : {}", ivision);
        if (ivision.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ivision result = ivisionService.save(ivision);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ivision.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ivisions} : get all the ivisions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ivisions in body.
     */
    @GetMapping("/ivisions")
    public ResponseEntity<List<Ivision>> getAllIvisions(Pageable pageable) {
        log.debug("REST request to get a page of Ivisions");
        Page<Ivision> page = ivisionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ivisions/:id} : get the "id" ivision.
     *
     * @param id the id of the ivision to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ivision, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ivisions/{id}")
    public ResponseEntity<Ivision> getIvision(@PathVariable Long id) {
        log.debug("REST request to get Ivision : {}", id);
        Optional<Ivision> ivision = ivisionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ivision);
    }

    /**
     * {@code DELETE  /ivisions/:id} : delete the "id" ivision.
     *
     * @param id the id of the ivision to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ivisions/{id}")
    public ResponseEntity<Void> deleteIvision(@PathVariable Long id) {
        log.debug("REST request to delete Ivision : {}", id);
        ivisionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
