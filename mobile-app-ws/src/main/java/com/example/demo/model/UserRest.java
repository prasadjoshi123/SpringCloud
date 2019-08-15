package com.example.demo.model;

public class UserRest {

	private String firstName;
	private String lastName;
	private String emailId;
	private String userId;

	@Override
	public String toString() {
		return "UserRest [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", userId="
				+ userId + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmailId()="
				+ getEmailId() + ", getUserId()=" + getUserId() + "]";
	}

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
