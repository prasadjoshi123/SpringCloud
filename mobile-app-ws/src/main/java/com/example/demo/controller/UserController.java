package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

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

import com.example.demo.model.UpdateUserDetails;
import com.example.demo.model.UserDetails;
import com.example.demo.model.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	Map<String, UserRest> users;
	
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit) {
		return "Get user was called for page " + page + "and limit " + limit;
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<?> getUser(@PathVariable String id) {
		
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
		UserRest userRest = new UserRest();

		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setEmailId(userDetails.getEmailId());

		String userId = UUID.randomUUID().toString();
		userRest.setUserId(userId);
		
		if(users == null) users = new HashMap<String, UserRest>();
		users.put(userId, userRest);
		
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
