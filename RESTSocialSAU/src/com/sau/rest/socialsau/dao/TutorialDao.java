package com.sau.rest.socialsau.dao;

import java.util.List;

import com.sau.rest.socialsau.dto.Tutorial;

public interface TutorialDao {

	public List<Tutorial> getAllTutorial();
	public Tutorial searchTutorial(Integer tutorialId);
	
}
