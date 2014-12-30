package com.sau.rest.socialsau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sau.rest.socialsau.dao.UserDao;
import com.sau.rest.socialsau.dto.User;
import com.sau.rest.socialsau.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserJSON(String email, String password) {
		return userDao.getUserJSON(email, password);
	}

	
	
}
