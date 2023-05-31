package com.example.demo.entity;

import javax.validation.constraints.NotBlank;

public class RecoverRequest {

	@NotBlank
	private String email;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
