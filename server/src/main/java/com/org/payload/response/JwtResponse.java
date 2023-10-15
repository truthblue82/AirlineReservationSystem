package com.org.payload.response;



import java.util.List;



public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String fullname;
	private String phone;


	public JwtResponse(String accessToken, Long id, String username, String email, String fullname, String phone, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.fullname = fullname;
		this.phone = phone;
	}



	public String getAccessToken() {
		return token;
	}



	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}



	public String getTokenType() {
		return type;
	}



	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<String> getRoles() {
		return roles;
	}
	public String getFullname() {return fullname;}
	public void setFullname(String fullname) { this.fullname = fullname;}
	public String getPhone() {return phone;}
	public void setPhone(String phone) { this.phone = phone;}
}