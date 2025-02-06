package com.janani.entity;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "survey_question")
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    @Column(name = "question_text", nullable = false)
    private String questionText = "";

    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "question_type", nullable = false)
    private String questionType = "";

    @Column(name = "is_required", nullable = false)
    private boolean isRequired = false;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "created_by", nullable = false)
    private String createdBy = "";

    @Column(name = "modified_at")
    private Date modifiedAt = new Date();

    @Column(name = "modified_by")
    private String modifiedBy;

    // Getters and setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
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

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, createdAt, createdBy, isRequired, modifiedAt, modifiedBy, questionId,
				questionText, questionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestion other = (SurveyQuestion) obj;
		return categoryId == other.categoryId && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(createdBy, other.createdBy) && isRequired == other.isRequired
				&& Objects.equals(modifiedAt, other.modifiedAt) && Objects.equals(modifiedBy, other.modifiedBy)
				&& questionId == other.questionId && Objects.equals(questionText, other.questionText)
				&& Objects.equals(questionType, other.questionType);
	}

	@Override
	public String toString() {
		return "SurveyQuestion [questionId=" + questionId + ", questionText=" + questionText + ", categoryId="
				+ categoryId + ", questionType=" + questionType + ", isRequired=" + isRequired + ", createdAt="
				+ createdAt + ", createdBy=" + createdBy + ", modifiedAt=" + modifiedAt + ", modifiedBy=" + modifiedBy
				+ "]";
	}
    
}
