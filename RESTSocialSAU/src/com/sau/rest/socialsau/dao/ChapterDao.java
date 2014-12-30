package com.sau.rest.socialsau.dao;

import java.util.List;

import com.sau.rest.socialsau.dto.Chapter;

public interface ChapterDao {

	public List<Chapter> findAllChapters(Integer tutorialId);
	public Chapter findChapter(Integer chapterId);
	
}
