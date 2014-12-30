package com.sau.rest.socialsau.service;

import java.util.List;

import com.sau.rest.socialsau.dto.Tutorial;

public interface TutorialService {

	public List<Tutorial> getAllTutorial();
	public Tutorial searchTutorial(Integer tutorialId);
	
}
