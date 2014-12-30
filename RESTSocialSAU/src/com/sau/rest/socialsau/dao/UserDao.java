package com.sau.rest.socialsau.dao;

import java.util.List;

import com.sau.rest.socialsau.dto.User;

public interface UserDao {

	public User getUserJSON(String email, String password);
	
}
