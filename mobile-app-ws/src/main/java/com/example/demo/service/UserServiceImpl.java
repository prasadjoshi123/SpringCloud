package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRest;
import com.example.demo.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	Utils utils;
	

	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}



	@Override
	public UserRest createUser(UserDetails userDetails) {
		UserRest userRest = new UserRest();

		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setEmailId(userDetails.getEmailId());

		String userId = utils.generateUserId();
		userRest.setUserId(userId);

		if (users == null)
			users = new HashMap<String, UserRest>();
		users.put(userId, userRest);

		return userRest;

	}

}
