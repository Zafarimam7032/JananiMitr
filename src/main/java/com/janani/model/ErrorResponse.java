package com.janani.model;

import java.util.Objects;

public class ErrorResponse {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		return Objects.equals(message, other.message);
	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
