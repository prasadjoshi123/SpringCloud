package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserServiceException;
import com.example.demo.model.UpdateUserDetails;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRest;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	Map<String, UserRest> users;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit) {
		return "Get user was called for page " + page + "and limit " + limit;
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<?> getUser(@PathVariable String id) {
		
		//if(true) throw new UserServiceException("A User Service Exception is thrown");
		
		if(users.containsKey(id)) {
			return new ResponseEntity<>(users.get(id), HttpStatus.OK);	
		}else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails) {
		
		UserRest userRest = userService.createUser(userDetails);
		
		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);

	}

	@PutMapping(path="/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public UserRest updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDetails userDetails) {
			UserRest storeUserDetails = users.get(id);
			storeUserDetails.setFirstName(userDetails.getFirstName());
			storeUserDetails.setLastName(userDetails.getLastName());
			users.put(id, storeUserDetails);
			return storeUserDetails;
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}
