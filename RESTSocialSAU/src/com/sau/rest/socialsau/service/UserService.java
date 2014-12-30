package com.sau.rest.socialsau.service;

import com.sau.rest.socialsau.dto.User;

public interface UserService {

	public User getUserJSON(String email, String password);
	
}
