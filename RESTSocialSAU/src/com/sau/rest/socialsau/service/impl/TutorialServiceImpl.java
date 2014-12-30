package com.sau.rest.socialsau.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sau.rest.socialsau.dao.TutorialDao;
import com.sau.rest.socialsau.dto.Tutorial;
import com.sau.rest.socialsau.service.TutorialService;

@Service("tutorialService")
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private TutorialDao tutorialDao;
	
	@Override
	public List<Tutorial> getAllTutorial() {
		return tutorialDao.getAllTutorial();
	}

	@Override
	public Tutorial searchTutorial(Integer tutorialId) {
		return tutorialDao.searchTutorial(tutorialId);
	}

}
