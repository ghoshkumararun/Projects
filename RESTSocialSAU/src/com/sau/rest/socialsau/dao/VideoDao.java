package com.sau.rest.socialsau.dao;

import java.util.List;

import com.sau.rest.socialsau.dto.Video;

public interface VideoDao {

	public List<Video> findAllVideo(Integer tutorialId);
	
}
