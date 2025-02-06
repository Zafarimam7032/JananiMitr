package com.janani.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "rch_id")
	private long rchId;

	@Column(name = "name")
	private String name;

	@Column(name = "mcp_card_number")
	private Integer mcpCardNumber;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "alternative_mobile_number")
	private String alternativeMobileNumber;

	@Column(name = "husband_mobile_number")
	private String husbandMobileNumber;

	@Column(name = "age")
	private Short age;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "height")
	private Double height;

	@Column(name = "weight")
	private Double weight;

	@Transient
	private Double bmi; // Computed column, should not be set directly

	@Column(name = "lmp_date")
	private Date lmpDate;

	@Transient
	private Date edd; // Computed column, should not be set directly

	@Column(name = "state")
	private String state;

	@Column(name = "district")
	private String district;

	@Column(name = "mandal")
	private String mandal;

	@Column(name = "village")
	private String village;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_at")
	private Date modifiedAt = new Date();

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "status")
	private Boolean status = true;

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

	public Integer getMcpCardNumber() {
		return mcpCardNumber;
	}

	public void setMcpCardNumber(Integer mcpCardNumber) {
		this.mcpCardNumber = mcpCardNumber;
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

	public String getHusbandMobileNumber() {
		return husbandMobileNumber;
	}

	public void setHusbandMobileNumber(String husbandMobileNumber) {
		this.husbandMobileNumber = husbandMobileNumber;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public Date getLmpDate() {
		return lmpDate;
	}

	public void setLmpDate(Date lmpDate) {
		this.lmpDate = lmpDate;
	}

	public Date getEdd() {
		return edd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public void setEdd(Date edd) {
		this.edd = edd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, alternativeMobileNumber, bmi, createdAt, createdBy, dateOfBirth, district, edd, height,
				husbandMobileNumber, latitude, lmpDate, longitude, mandal, mcpCardNumber, mobileNumber, modifiedAt,
				modifiedBy, name, pincode, rchId, state, status, village, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(age, other.age) && Objects.equals(alternativeMobileNumber, other.alternativeMobileNumber)
				&& Objects.equals(bmi, other.bmi) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(district, other.district) && Objects.equals(edd, other.edd)
				&& Objects.equals(height, other.height)
				&& Objects.equals(husbandMobileNumber, other.husbandMobileNumber)
				&& Objects.equals(latitude, other.latitude) && Objects.equals(lmpDate, other.lmpDate)
				&& Objects.equals(longitude, other.longitude) && Objects.equals(mandal, other.mandal)
				&& Objects.equals(mcpCardNumber, other.mcpCardNumber)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(modifiedAt, other.modifiedAt)
				&& Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(name, other.name)
				&& Objects.equals(pincode, other.pincode) && rchId == other.rchId && Objects.equals(state, other.state)
				&& Objects.equals(status, other.status) && Objects.equals(village, other.village)
				&& Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "User [rchId=" + rchId + ", name=" + name + ", mcpCardNumber=" + mcpCardNumber + ", mobileNumber="
				+ mobileNumber + ", alternativeMobileNumber=" + alternativeMobileNumber + ", husbandMobileNumber="
				+ husbandMobileNumber + ", age=" + age + ", dateOfBirth=" + dateOfBirth + ", height=" + height
				+ ", weight=" + weight + ", bmi=" + bmi + ", lmpDate=" + lmpDate + ", edd=" + edd + ", state=" + state
				+ ", district=" + district + ", mandal=" + mandal + ", village=" + village + ", pincode=" + pincode
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", createdAt=" + createdAt + ", createdBy="
				+ createdBy + ", modifiedAt=" + modifiedAt + ", modifiedBy=" + modifiedBy + ", status=" + status + "]";
	}

}
