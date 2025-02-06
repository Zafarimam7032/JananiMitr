package com.janani.model;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {
	private String classification;
	private double longitude;
	private double latitude;
	private String description;
	private long rchId;
	private String photoPath;
	private String photoName;
	private MultipartFile formFile;

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getRchId() {
		return rchId;
	}

	public void setRchId(long rchId) {
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

	public MultipartFile getFormFile() {
		return formFile;
	}

	public void setFormFile(MultipartFile formFile) {
		this.formFile = formFile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classification, description, formFile, latitude, longitude, photoName, photoPath, rchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageDTO other = (ImageDTO) obj;
		return Objects.equals(classification, other.classification) && Objects.equals(description, other.description)
				&& Objects.equals(formFile, other.formFile)
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& Objects.equals(photoName, other.photoName) && Objects.equals(photoPath, other.photoPath)
				&& rchId == other.rchId;
	}

	@Override
	public String toString() {
		return "ImageDTO [classification=" + classification + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", description=" + description + ", rchId=" + rchId + ", photoPath=" + photoPath + ", photoName="
				+ photoName + ", formFile=" + formFile + "]";
	}

}
