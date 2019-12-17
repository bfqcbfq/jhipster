package com.ivision.app.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ivision.app.repository.MitsubishiRepository;
import com.ivision.app.service.MitsubishiService;

public class Demo {
	
//	@Autowired
//	MitsubishiService  mitsubishiService;
	
	public static void main(String[] args) {
		
		MitsubishiService mitsubishiService = new MitsubishiService(null);
		
	}

}
