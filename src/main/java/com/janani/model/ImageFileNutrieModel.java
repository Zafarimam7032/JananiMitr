package com.janani.model;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class ImageFileNutrieModel {
    private long rchId;
    private MultipartFile formFile;

    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public MultipartFile getFormFile() {
        return formFile;
    }

    public void setFormFile(MultipartFile formFile) {
        this.formFile = formFile;
    }

	@Override
	public int hashCode() {
		return Objects.hash(formFile, rchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageFileNutrieModel other = (ImageFileNutrieModel) obj;
		return Objects.equals(formFile, other.formFile) && rchId == other.rchId;
	}

	@Override
	public String toString() {
		return "ImageFileNutrieModel [rchId=" + rchId + ", formFile=" + formFile + "]";
	}
    
    
}
