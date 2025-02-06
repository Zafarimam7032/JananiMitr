
package com.janani.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questionoptions", schema = "public")
public class QuestionOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private int optionId;

	@Column(name = "question_id", nullable = false)
	private int questionId;

	@Column(name = "option_text", length = 255, nullable = false)
	private String optionText;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private Date modifiedAt = new Date();

	@Column(name = "modified_by", length = 100)
	private String modifiedBy;

	// Navigation property
	@ManyToOne
	@JoinColumn(name = "survey_question_id")
	private SurveyQuestion surveyQuestion;

	// Getters and setters
	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
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

	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, createdBy, modifiedAt, modifiedBy, optionId, optionText, questionId,
				surveyQuestion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionOption other = (QuestionOption) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(modifiedAt, other.modifiedAt) && Objects.equals(modifiedBy, other.modifiedBy)
				&& optionId == other.optionId && Objects.equals(optionText, other.optionText)
				&& questionId == other.questionId && Objects.equals(surveyQuestion, other.surveyQuestion);
	}

	@Override
	public String toString() {
		return "QuestionOption [optionId=" + optionId + ", questionId=" + questionId + ", optionText=" + optionText
				+ ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", modifiedAt=" + modifiedAt
				+ ", modifiedBy=" + modifiedBy + ", surveyQuestion=" + surveyQuestion + "]";
	}
	
}
