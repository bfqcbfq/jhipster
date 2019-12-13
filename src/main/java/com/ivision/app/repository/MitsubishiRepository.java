package com.ivision.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivision.app.domain.MitsubishiSurvey;

@SuppressWarnings("unused")
@Repository
public interface MitsubishiRepository extends JpaRepository<MitsubishiSurvey,Long> {
	

}
