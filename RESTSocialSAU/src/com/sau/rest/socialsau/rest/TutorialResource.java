package com.sau.rest.socialsau.rest;

import java.util.ArrayList;
import java.util.Date;
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

import com.sau.rest.socialsau.dto.Tutorial;
import com.sau.rest.socialsau.dto.User;
import com.sau.rest.socialsau.service.TutorialService;

@Path("/tutorial")
@Component("tutorialResource")
@Scope("prototype")
public class TutorialResource {

	@Autowired
	private TutorialService tutorialService;
	
	@POST
	@Path("/getAllTutorialJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tutorial> getAllTutorial() {
		return tutorialService.getAllTutorial();
	}
	
	@POST
	@Path("/searchTutorialJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutorial searchTutorial(@FormParam("tutorialId") Integer tutorialId) {
		if (tutorialId != null) {
			return tutorialService.searchTutorial(tutorialId);
		}
		return null;
	}
	
}
