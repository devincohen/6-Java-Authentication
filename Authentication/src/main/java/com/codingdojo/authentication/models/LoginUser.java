package com.codingdojo.authentication.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUser {
	@NotBlank (message="Email is required!")
	@Email(message="Please enter a valid email")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
	
	public LoginUser() {}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
