package com.janani.model;
import java.util.Date;
import java.util.Objects;

public class SurveyResponseProjected {
    private long rchId;
    private String categoryName;
    private String questionText;
    private String optionText;
    private String createdAt;

    // Getters and setters
    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

	@Override
	public int hashCode() {
		return Objects.hash(categoryName, createdAt, optionText, questionText, rchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyResponseProjected other = (SurveyResponseProjected) obj;
		return Objects.equals(categoryName, other.categoryName) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(optionText, other.optionText) && Objects.equals(questionText, other.questionText)
				&& rchId == other.rchId;
	}

	@Override
	public String toString() {
		return "SurveyResponseProjected [rchId=" + rchId + ", categoryName=" + categoryName + ", questionText="
				+ questionText + ", optionText=" + optionText + ", createdAt=" + createdAt + "]";
	}

	public SurveyResponseProjected() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurveyResponseProjected(long rchId, String categoryName, String questionText, String optionText,
			String createdAt) {
		super();
		this.rchId = rchId;
		this.categoryName = categoryName;
		this.questionText = questionText;
		this.optionText = optionText;
		this.createdAt = createdAt;
	}
	
    
    
}
