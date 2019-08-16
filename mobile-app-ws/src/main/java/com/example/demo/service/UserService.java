package com.example.demo.service;

import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRest;

public interface UserService {
	
	public UserRest createUser(UserDetails userDetails);

}
