package com.example.demo.model;

import javax.validation.constraints.NotNull;

public class UpdateUserDetails {

	@NotNull(message = "First name can not be null")
	private String firstName;
	
	@NotNull (message = "Last name can not be null")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
