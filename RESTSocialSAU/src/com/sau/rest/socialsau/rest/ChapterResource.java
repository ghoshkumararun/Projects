package com.sau.rest.socialsau.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sau.rest.socialsau.dto.Chapter;
import com.sau.rest.socialsau.service.ChapterService;

@Path("/chapter")
@Component("chapterResource")
@Scope("prototype")
public class ChapterResource {

	@Autowired
	private ChapterService chapterService;
	
	@POST
	@Path("/findAllChaptersJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chapter> findAllChapters(@FormParam("tutorialId") Integer tutorialId) {
		if (tutorialId != null) {
			return chapterService.findAllChapters(tutorialId);
		}
		return null;
	}
	
	@POST
	@Path("/findChapterJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public Chapter findChapter(@FormParam("chapterId") Integer chapterId) {
		if (chapterId != null) {
			return chapterService.findChapter(chapterId);
		}
		return null;
	}
	
}
