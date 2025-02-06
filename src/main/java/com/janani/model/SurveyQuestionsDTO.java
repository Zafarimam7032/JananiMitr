package com.janani.model;
import java.util.List;
import java.util.Objects;

import com.janani.entity.QuestionOption;

public class SurveyQuestionsDTO {
    private long rchId;
    private int categoryId;
    private int questionId;
    private String questionText = "";  // Default empty string equivalent to C#'s string.Empty
    private String questionType;
    private List<QuestionOption> questionOptions;

    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, questionId, questionOptions, questionText, questionType, rchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionsDTO other = (SurveyQuestionsDTO) obj;
		return categoryId == other.categoryId && questionId == other.questionId
				&& Objects.equals(questionOptions, other.questionOptions)
				&& Objects.equals(questionText, other.questionText) && Objects.equals(questionType, other.questionType)
				&& rchId == other.rchId;
	}

	@Override
	public String toString() {
		return "SurveyQuestionsDTO [rchId=" + rchId + ", categoryId=" + categoryId + ", questionId=" + questionId
				+ ", questionText=" + questionText + ", questionType=" + questionType + ", questionOptions="
				+ questionOptions + "]";
	}
    
    
}
