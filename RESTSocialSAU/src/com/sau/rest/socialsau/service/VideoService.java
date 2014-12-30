package com.sau.rest.socialsau.service;

import java.util.List;

import com.sau.rest.socialsau.dto.Video;

public interface VideoService {

	public List<Video> findAllVideo(Integer tutorialId);
	
}
