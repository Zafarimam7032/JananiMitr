package com.janani.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class CumulativeRiskScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date scoreGeneratedDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    private Integer riskScore;
    private String category;
    private long rchId;
    private Integer questionId;
    private Integer optionId;
    private String finalRiskValue;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getScoreGeneratedDate() {
        return scoreGeneratedDate;
    }

    public void setScoreGeneratedDate(Date scoreGeneratedDate) {
        this.scoreGeneratedDate = scoreGeneratedDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getFinalRiskValue() {
        return finalRiskValue;
    }

    public void setFinalRiskValue(String finalRiskValue) {
        this.finalRiskValue = finalRiskValue;
    }

	@Override
	public int hashCode() {
		return Objects.hash(category, createdAt, finalRiskValue, id, optionId, questionId, rchId, riskScore,
				scoreGeneratedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CumulativeRiskScore other = (CumulativeRiskScore) obj;
		return Objects.equals(category, other.category) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(finalRiskValue, other.finalRiskValue) && id == other.id
				&& Objects.equals(optionId, other.optionId) && Objects.equals(questionId, other.questionId)
				&& rchId == other.rchId && Objects.equals(riskScore, other.riskScore)
				&& Objects.equals(scoreGeneratedDate, other.scoreGeneratedDate);
	}

	@Override
	public String toString() {
		return "CumulativeRiskScore [id=" + id + ", scoreGeneratedDate=" + scoreGeneratedDate + ", createdAt="
				+ createdAt + ", riskScore=" + riskScore + ", category=" + category + ", rchId=" + rchId
				+ ", questionId=" + questionId + ", optionId=" + optionId + ", finalRiskValue=" + finalRiskValue + "]";
	}
    
    
}
