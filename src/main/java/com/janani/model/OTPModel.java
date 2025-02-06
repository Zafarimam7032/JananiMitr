package com.janani.model;

import java.util.Objects;

public class OTPModel {
	private String phoneNo;
	private String requestedFrom;
	private String type;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRequestedFrom() {
		return requestedFrom;
	}

	public void setRequestedFrom(String requestedFrom) {
		this.requestedFrom = requestedFrom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneNo, requestedFrom, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OTPModel other = (OTPModel) obj;
		return Objects.equals(phoneNo, other.phoneNo) && Objects.equals(requestedFrom, other.requestedFrom)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "OTPModel [phoneNo=" + phoneNo + ", requestedFrom=" + requestedFrom + ", type=" + type + "]";
	}

}
