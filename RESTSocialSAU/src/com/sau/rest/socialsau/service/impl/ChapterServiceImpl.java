package com.sau.rest.socialsau.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sau.rest.socialsau.dao.ChapterDao;
import com.sau.rest.socialsau.dto.Chapter;
import com.sau.rest.socialsau.service.ChapterService;

@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterDao chapterDao;
	
	@Override
	public List<Chapter> findAllChapters(Integer tutorialId) {
		return chapterDao.findAllChapters(tutorialId);
	}

	@Override
	public Chapter findChapter(Integer chapterId) {
		return chapterDao.findChapter(chapterId);
	}

	
	
}
