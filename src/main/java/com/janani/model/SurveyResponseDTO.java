package com.janani.model;

import java.util.List;
import java.util.Objects;

public class SurveyResponseDTO {
	private long rchId;
	private int questionId;
	private List<SurveyOptionDTO> questionOptions;

	// Getters and setters
	public long getRchId() {
		return rchId;
	}

	public void setRchId(long rchId) {
		this.rchId = rchId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<SurveyOptionDTO> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<SurveyOptionDTO> questionOptions) {
		this.questionOptions = questionOptions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(questionId, questionOptions, rchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyResponseDTO other = (SurveyResponseDTO) obj;
		return questionId == other.questionId && Objects.equals(questionOptions, other.questionOptions)
				&& rchId == other.rchId;
	}

	@Override
	public String toString() {
		return "SurveyResponseDTO [rchId=" + rchId + ", questionId=" + questionId + ", questionOptions="
				+ questionOptions + "]";
	}

}
