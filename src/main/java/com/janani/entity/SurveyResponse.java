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
@Table(name = "survey_response")
public class SurveyResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "response_id")
	private int responseId;

	@Column(name = "question_id", nullable = false)
	private int questionId;

	@Column(name = "rchid", nullable = false)
	private long rchId;

	@Column(name = "response_text", length = 255)
	private String responseText;

	@Column(name = "option_id")
	private Integer optionId;

	@Column(name = "response_date")
	private Date responseDate = new Date();

	@Column(name = "created_at")
	private Date createdAt = new Date();

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private Date modifiedAt = new Date();

	@Column(name = "modified_by", length = 100)
	private String modifiedBy;

	// Navigation properties
	@ManyToOne
	@JoinColumn(name = "question_option_id")
	private QuestionOption questionOption;

	@ManyToOne
	@JoinColumn(name = "survey_question_id")
	private SurveyQuestion surveyQuestion;

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public long getRchId() {
		return rchId;
	}

	public void setRchId(long rchId) {
		this.rchId = rchId;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
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

	public QuestionOption getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}

	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, createdBy, modifiedAt, modifiedBy, optionId, questionId, questionOption, rchId,
				responseDate, responseId, responseText, surveyQuestion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyResponse other = (SurveyResponse) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(modifiedAt, other.modifiedAt) && Objects.equals(modifiedBy, other.modifiedBy)
				&& Objects.equals(optionId, other.optionId) && questionId == other.questionId
				&& Objects.equals(questionOption, other.questionOption) && rchId == other.rchId
				&& Objects.equals(responseDate, other.responseDate) && responseId == other.responseId
				&& Objects.equals(responseText, other.responseText)
				&& Objects.equals(surveyQuestion, other.surveyQuestion);
	}

	@Override
	public String toString() {
		return "SurveyResponse [responseId=" + responseId + ", questionId=" + questionId + ", rchId=" + rchId
				+ ", responseText=" + responseText + ", optionId=" + optionId + ", responseDate=" + responseDate
				+ ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", modifiedAt=" + modifiedAt
				+ ", modifiedBy=" + modifiedBy + ", questionOption=" + questionOption + ", surveyQuestion="
				+ surveyQuestion + "]";
	}
	
	 
	
}
