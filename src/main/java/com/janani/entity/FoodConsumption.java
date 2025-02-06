package com.janani.entity;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodConsumption {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long rchId;
    private String category;
    private Boolean proteinTaken;
    private Boolean carbohydrateTaken;
    private Boolean fatsTaken;
    private Boolean caloriesTaken;
    private Integer calories;
    private Integer actual;
    private int planned = 5;
    private Date consumptionDate = new Date();
    private String riskValue;
    private Integer riskScorePercentage;
    private TimeZone consumptionTime = TimeZone.getDefault();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRchId() {
        return rchId;
    }

    public void setRchId(long rchId) {
        this.rchId = rchId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getProteinTaken() {
        return proteinTaken;
    }

    public void setProteinTaken(Boolean proteinTaken) {
        this.proteinTaken = proteinTaken;
    }

    public Boolean getCarbohydrateTaken() {
        return carbohydrateTaken;
    }

    public void setCarbohydrateTaken(Boolean carbohydrateTaken) {
        this.carbohydrateTaken = carbohydrateTaken;
    }

    public Boolean getFatsTaken() {
        return fatsTaken;
    }

    public void setFatsTaken(Boolean fatsTaken) {
        this.fatsTaken = fatsTaken;
    }

    public Boolean getCaloriesTaken() {
        return caloriesTaken;
    }

    public void setCaloriesTaken(Boolean caloriesTaken) {
        this.caloriesTaken = caloriesTaken;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(String riskValue) {
        this.riskValue = riskValue;
    }

    public Integer getRiskScorePercentage() {
        return riskScorePercentage;
    }

    public void setRiskScorePercentage(Integer riskScorePercentage) {
        this.riskScorePercentage = riskScorePercentage;
    }

    public TimeZone getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(TimeZone consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

	@Override
	public int hashCode() {
		return Objects.hash(actual, calories, caloriesTaken, carbohydrateTaken, category, consumptionDate,
				consumptionTime, fatsTaken, id, planned, proteinTaken, rchId, riskScorePercentage, riskValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodConsumption other = (FoodConsumption) obj;
		return Objects.equals(actual, other.actual) && Objects.equals(calories, other.calories)
				&& Objects.equals(caloriesTaken, other.caloriesTaken)
				&& Objects.equals(carbohydrateTaken, other.carbohydrateTaken)
				&& Objects.equals(category, other.category) && Objects.equals(consumptionDate, other.consumptionDate)
				&& Objects.equals(consumptionTime, other.consumptionTime) && Objects.equals(fatsTaken, other.fatsTaken)
				&& id == other.id && planned == other.planned && Objects.equals(proteinTaken, other.proteinTaken)
				&& rchId == other.rchId && Objects.equals(riskScorePercentage, other.riskScorePercentage)
				&& Objects.equals(riskValue, other.riskValue);
	}

	@Override
	public String toString() {
		return "FoodConsumption [id=" + id + ", rchId=" + rchId + ", category=" + category + ", proteinTaken="
				+ proteinTaken + ", carbohydrateTaken=" + carbohydrateTaken + ", fatsTaken=" + fatsTaken
				+ ", caloriesTaken=" + caloriesTaken + ", calories=" + calories + ", actual=" + actual + ", planned="
				+ planned + ", consumptionDate=" + consumptionDate + ", riskValue=" + riskValue
				+ ", riskScorePercentage=" + riskScorePercentage + ", consumptionTime=" + consumptionTime + "]";
	}
}

