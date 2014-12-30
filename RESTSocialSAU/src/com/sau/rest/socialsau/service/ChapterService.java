package com.sau.rest.socialsau.service;

import java.util.List;

import com.sau.rest.socialsau.dto.Chapter;

public interface ChapterService {

	public List<Chapter> findAllChapters(Integer tutorialId);
	public Chapter findChapter(Integer chapterId);
	
}
