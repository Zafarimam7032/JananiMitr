package com.janani.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "photo", schema = "public")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photoid")
	private int photoId;

	@Column(name = "classification", length = 20, nullable = false)
	private String classification;

	@Column(name = "capturedatetime")
	private Date captureDateTime = new Date();

	@Column(name = "longitude", columnDefinition = "numeric(9,6)")
	private Double longitude;

	@Column(name = "latitude", columnDefinition = "numeric(9,6)")
	private Double latitude;

	@Column(name = "description")
	private String description;

	@Column(name = "createdat")
	private Date createdAt = new Date();

	@Column(name = "createdby", length = 100, nullable = false)
	private String createdBy;

	@Column(name = "modifiedat")
	private Date modifiedAt = new Date();

	@Column(name = "modifiedby", length = 100)
	private String modifiedBy;

	@Column(name = "rchid")
	private Long rchId;

	@Column(name = "photo_path", length = 255)
	private String photoPath;

	@Column(name = "photoname", length = 255)
	private String photoName;

	@Column(name = "is_asha_image")
	private Boolean isAshaImage;

	// Navigation property (optional)
	@ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "rch_id")
	private User user;

	// Getters and setters
	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Date getCaptureDateTime() {
		return captureDateTime;
	}

	public void setCaptureDateTime(Date captureDateTime) {
		this.captureDateTime = captureDateTime;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getRchId() {
		return rchId;
	}

	public void setRchId(Long rchId) {
		this.rchId = rchId;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Boolean getIsAshaImage() {
		return isAshaImage;
	}

	public void setIsAshaImage(Boolean isAshaImage) {
		this.isAshaImage = isAshaImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Image [photoId=" + photoId + ", classification=" + classification + ", captureDateTime="
				+ captureDateTime + ", longitude=" + longitude + ", latitude=" + latitude + ", description="
				+ description + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", modifiedAt=" + modifiedAt
				+ ", modifiedBy=" + modifiedBy + ", rchId=" + rchId + ", photoPath=" + photoPath + ", photoName="
				+ photoName + ", isAshaImage=" + isAshaImage + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(captureDateTime, classification, createdAt, createdBy, description, isAshaImage, latitude,
				longitude, modifiedAt, modifiedBy, photoId, photoName, photoPath, rchId, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(captureDateTime, other.captureDateTime)
				&& Objects.equals(classification, other.classification) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(description, other.description)
				&& Objects.equals(isAshaImage, other.isAshaImage) && Objects.equals(latitude, other.latitude)
				&& Objects.equals(longitude, other.longitude) && Objects.equals(modifiedAt, other.modifiedAt)
				&& Objects.equals(modifiedBy, other.modifiedBy) && photoId == other.photoId
				&& Objects.equals(photoName, other.photoName) && Objects.equals(photoPath, other.photoPath)
				&& Objects.equals(rchId, other.rchId) && Objects.equals(user, other.user);
	}

	public Image(int photoId, String classification, Date captureDateTime, Double longitude, Double latitude,
			String description, Date createdAt, String createdBy, Date modifiedAt, String modifiedBy, Long rchId,
			String photoPath, String photoName, Boolean isAshaImage, User user) {
		super();
		this.photoId = photoId;
		this.classification = classification;
		this.captureDateTime = captureDateTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
		this.rchId = rchId;
		this.photoPath = photoPath;
		this.photoName = photoName;
		this.isAshaImage = isAshaImage;
		this.user = user;
	}
	
	public Image() {
		super();
	}
	
}
