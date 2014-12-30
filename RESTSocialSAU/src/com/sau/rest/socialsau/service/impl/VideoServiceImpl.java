package com.sau.rest.socialsau.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sau.rest.socialsau.dao.VideoDao;
import com.sau.rest.socialsau.dto.Video;
import com.sau.rest.socialsau.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;

	@Override
	public List<Video> findAllVideo(Integer tutorialId) {
		return videoDao.findAllVideo(tutorialId);
	}
	
	
	
}
