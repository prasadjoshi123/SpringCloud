package com.example.photoapp.api.users.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.photoapp.api.users.service.UsersService;
import com.example.photoapp.api.users.shared.UserDto;
import com.example.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.example.photoapp.api.users.ui.model.CreateuserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;
	
	@Autowired
	private UsersService userService; 
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port =" + env.getProperty("local.server.port") + ", with token ="+ env.getProperty("token.secret");
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<CreateuserResponseModel> CreateUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = mapper.map(userDetails, UserDto.class);
		UserDto createduser = userService.createUser(userDto);
		CreateuserResponseModel createuserResponseModel = mapper.map(createduser, CreateuserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createuserResponseModel); 	
	}
}
