package com.example.demo.apiCommon;

import java.util.Collection;

public class UpdateUserRequest {

	private String city;
	private String email;
	private String postalCode;
	private Collection<String> roles;
	private String state;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRole(Collection<String> roles) {
		this.roles = roles;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
