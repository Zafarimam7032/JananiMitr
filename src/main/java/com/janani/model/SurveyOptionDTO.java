package com.janani.model;

import java.util.Objects;

public class SurveyOptionDTO {
	private int optionId;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(optionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyOptionDTO other = (SurveyOptionDTO) obj;
		return optionId == other.optionId;
	}

	@Override
	public String toString() {
		return "SurveyOptionDTO [optionId=" + optionId + "]";
	}

}
