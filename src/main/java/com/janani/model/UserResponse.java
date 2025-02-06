package com.janani.model;

import java.util.Objects;

import com.janani.entity.User;

// Define the UserResponse class to store the JWT token and user details
public class UserResponse {
	private String token;
	private User userResponse;

	public UserResponse(String token, User userResponse) {
		this.token = token;
		this.userResponse = userResponse;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(User userResponse) {
		this.userResponse = userResponse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, userResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return Objects.equals(token, other.token) && Objects.equals(userResponse, other.userResponse);
	}

	@Override
	public String toString() {
		return "UserResponse [token=" + token + ", userResponse=" + userResponse + "]";
	}
	
	
}