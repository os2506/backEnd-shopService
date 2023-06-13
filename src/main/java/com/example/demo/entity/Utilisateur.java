package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Utilisateur implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("confirmPassword")
	private String confirmPassword;
	@JsonProperty("email")
	private String email;
	@JsonProperty("subscribe")
	private Boolean subscribe;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("postalCode")
	private String postalCode;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRoles> roles=new ArrayList<>();
	
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(Long id, String username, String password, String confirmPassword, String email,
			Boolean subscribe, String city, String state, String postalCode, Collection<AppRoles> roles) {
		//super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.subscribe = subscribe;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.roles = roles;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Collection<AppRoles> getRoles() {
		return roles;
	}
	public void setRoles(Collection<AppRoles> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + ", subscribe=" + subscribe + ", city=" + city + ", state="
				+ state + ", postalCode=" + postalCode + ", roles=" + roles + "]";
	}
}
