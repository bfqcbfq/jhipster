package com.ivision.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivision.app.domain.MitsubishiSurvey;
import com.ivision.app.service.MitsubishiService;

import io.github.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api/mc")
public class MitsubishiResource {
	
	 private final Logger log = LoggerFactory.getLogger(MitsubishiResource.class);

	    private static final String ENTITY_NAME = "mitsubishi_survey";

	    @Value("${jhipster.clientApp.name}")
	    private String applicationName;

	    private final MitsubishiService mitsubishiService;

	    public MitsubishiResource(MitsubishiService mitsubishiService) {
	        this.mitsubishiService = mitsubishiService;
	    }
	    
	    @GetMapping("/mhi")
	    public ResponseEntity<MitsubishiSurvey> save() throws URISyntaxException {
//	        log.debug("REST request to save mitsubishiSurvey : {}", mitsubishiSurvey);
//	        if (mitsubishiSurvey.getId() != null) {
//	            throw new BadRequestAlertException("A new ivision cannot already have an ID", ENTITY_NAME, "idexists");
//	        }
	        MitsubishiSurvey mitsubishiSurvey = new MitsubishiSurvey();
	        
	        //mitsubishiSurvey.setId(1);
	        mitsubishiSurvey.setMitsubishiName("王三石");
	        
	        MitsubishiSurvey result = mitsubishiService.save(mitsubishiSurvey);
	        return ResponseEntity.ok(result);
	    }

}
