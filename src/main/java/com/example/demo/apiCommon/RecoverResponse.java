package com.example.demo.apiCommon;

public class RecoverResponse {
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RecoverResponse(String email) {
		super();
		this.email = email;
	}
}
