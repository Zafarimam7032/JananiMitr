package com.janani.model;

import java.util.Date;
import java.util.Objects;

public class UserViewModel {
	private long rchId;
	private String name;
	private String mobileNumber;
	private String alternativeMobileNumber;
	private Date dateOfBirth;
	private Double height;
	private Double heightIn;
	private Double weight;
	private Date lmpDate;
	private String village;

	// Getters and setters
	public long getRchId() {
		return rchId;
	}

	public void setRchId(long rchId) {
		this.rchId = rchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAlternativeMobileNumber() {
		return alternativeMobileNumber;
	}

	public void setAlternativeMobileNumber(String alternativeMobileNumber) {
		this.alternativeMobileNumber = alternativeMobileNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getHeightIn() {
		return heightIn;
	}

	public void setHeightIn(Double heightIn) {
		this.heightIn = heightIn;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getLmpDate() {
		return lmpDate;
	}

	public void setLmpDate(Date lmpDate) {
		this.lmpDate = lmpDate;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alternativeMobileNumber, dateOfBirth, height, heightIn, lmpDate, mobileNumber, name, rchId,
				village, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserViewModel other = (UserViewModel) obj;
		return Objects.equals(alternativeMobileNumber, other.alternativeMobileNumber)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(height, other.height)
				&& Objects.equals(heightIn, other.heightIn) && Objects.equals(lmpDate, other.lmpDate)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(name, other.name)
				&& rchId == other.rchId && Objects.equals(village, other.village)
				&& Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "UserViewModel [rchId=" + rchId + ", name=" + name + ", mobileNumber=" + mobileNumber
				+ ", alternativeMobileNumber=" + alternativeMobileNumber + ", dateOfBirth=" + dateOfBirth + ", height="
				+ height + ", heightIn=" + heightIn + ", weight=" + weight + ", lmpDate=" + lmpDate + ", village="
				+ village + "]";
	}
	
	
}
