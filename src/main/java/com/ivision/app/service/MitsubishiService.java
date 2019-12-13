package com.ivision.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivision.app.domain.MitsubishiSurvey;
import com.ivision.app.repository.MitsubishiRepository;

@Service
@Transactional
public class MitsubishiService {
	
	private final Logger log = LoggerFactory.getLogger(MitsubishiService.class);
	
	private final MitsubishiRepository mitsubishiRepository;

	public MitsubishiService(MitsubishiRepository mitsubishiRepository) {
		this.mitsubishiRepository = mitsubishiRepository;
	}
	
	private MitsubishiSurvey save(MitsubishiSurvey mutsubishiSurvey) {
		
		return mitsubishiRepository.save(mutsubishiSurvey);
	}
	
	
	

}
