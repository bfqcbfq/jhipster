package com.ivision.app.service;

import java.util.List;

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

	public MitsubishiSurvey save(MitsubishiSurvey mutsubishiSurvey) {

		return mitsubishiRepository.save(mutsubishiSurvey);
	}

	public List<MitsubishiSurvey> find() {

		return mitsubishiRepository.findAll();
	}

	public List<MitsubishiSurvey> findByFilepath(String filepath) {

		
//		List<MitsubishiSurvey> msList = mitsubishiRepository.findByFilepath(filepath);
//		return mitsubishiRepository.findAll();
		
		
		return mitsubishiRepository.findByFilepath(filepath);
	}

	public void updateByFilepath(String filepath) {

//		return mitsubishiRepository.findAll();
		mitsubishiRepository.updateByFilepath(filepath);
	}
	
	public MitsubishiSurvey findUpdatedByFilepath(String filepath) {
		
		return mitsubishiRepository.findUpdatedByFilepath(filepath);
		
	}

}
