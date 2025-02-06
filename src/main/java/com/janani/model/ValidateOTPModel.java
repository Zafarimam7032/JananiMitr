package com.janani.model;

import java.util.Objects;

public class ValidateOTPModel {
	private String phoneNo;
	private String requestId;
	private String otp;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(otp, phoneNo, requestId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidateOTPModel other = (ValidateOTPModel) obj;
		return Objects.equals(otp, other.otp) && Objects.equals(phoneNo, other.phoneNo)
				&& Objects.equals(requestId, other.requestId);
	}

	@Override
	public String toString() {
		return "ValidateOTPModel [phoneNo=" + phoneNo + ", requestId=" + requestId + ", otp=" + otp + "]";
	}
	
	
}
