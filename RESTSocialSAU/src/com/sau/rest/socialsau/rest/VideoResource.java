package com.sau.rest.socialsau.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sau.rest.socialsau.dto.Video;
import com.sau.rest.socialsau.service.VideoService;

@Path("/video")
@Component("videoResource")
@Scope("prototype")
public class VideoResource {

	@Autowired
	private VideoService videoService;
	
	@POST
	@Path("/findAllVideoJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Video> findAllVideo(@FormParam("tutorialId") Integer tutorialId) {
		if (tutorialId != null) {
			return videoService.findAllVideo(tutorialId);
		}
		return null;
	}
	
}
