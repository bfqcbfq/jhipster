package com.ivision.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ivision.app.domain.MitsubishiSurvey;


@SuppressWarnings("unused")
@Repository
public interface MitsubishiRepository extends JpaRepository<MitsubishiSurvey, Long> {

	@Query(value = "from MitsubishiSurvey where filepath = ?1")
	public List<MitsubishiSurvey> findByFilepath(String filepath);
	
	@Query(value = "from MitsubishiSurvey where filepath = ?1 and updateFlag = null")
	public MitsubishiSurvey findUpdatedByFilepath(String filepath);

	
//	 @Query("update MitsubishiSurvey set mitsubishiName = ?16, mitsubishiCompanyName = ?15,mitsubishiTelphone = ?14, mitsubishiEmail = ?13,  questionOne = ?12, questionTwo = ?11, questionThree = ?10, questionFour = ?9,questionFive = ?8, questionSix = ?7, questionSeven = ?6, questionEight = ?5, questionNine = ?4, questionTen ?3, mitsubishiComment = ?2 where filepath = ?1"
//	 )
//	  
//	  @Modifying public void updateByFilepath(String filepath, String
//	  mitsubishiComment, String questionTen, String questionNine, String
//	  questionEight, String questionSeven, String questionSix, String questionFive,
//	  String questionFour, String questionThree, String questionTwo, String
//	  questionOne, String mitsubishiEmail, String mitsubishiTelphone, String
//	  mitsubishiCompanyName, String mitsubishiName);
	 
	
	@Query("update MitsubishiSurvey  set updateFlag = '1' where filepath = ?1")
	@Modifying
	public void updateByFilepath(String filepath);


}
