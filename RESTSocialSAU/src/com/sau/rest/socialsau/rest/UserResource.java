package com.sau.rest.socialsau.rest;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sau.rest.socialsau.dto.User;
import com.sau.rest.socialsau.service.UserService;

@Path("/user")
@Component("userResource")
@Scope("prototype")
public class UserResource {

	// GET defines a reading access of the resource without side-effects. 
	// The resource is never changed via a GET request, e.g., the request has no side effects (idempotent).
	
	// POST updates an existing resource or creates a new resource.
	
	// PUT creates a new resource. It must also be idempotent.
	
	// DELETE removes the resources. The operations are idempotent. They can get repeated without leading to different results.
	
	@Autowired
	private UserService userService;
	private User user;
	
	@POST
	@Path("/getUserJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserJSON(@FormParam("email") String email, @FormParam("password") String password) {
		if (email != null && !email.trim().equals("") && email != null && !password.trim().equals("")) {
			user = userService.getUserJSON(email, password);
		}
		return user;
	}
	
}
