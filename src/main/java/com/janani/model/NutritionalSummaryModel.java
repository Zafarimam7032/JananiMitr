package com.janani.model;

import java.util.Objects;

public class NutritionalSummaryModel {
    private double totalCalories;
    private double totalCarbohydrates;
    private double totalProteins;
    private double totalFats;

    public NutritionalSummaryModel(double totalCalories, double totalCarbohydrates, double totalProteins, double totalFats) {
        this.totalCalories = totalCalories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalProteins = totalProteins;
        this.totalFats = totalFats;
    }

	public double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}

	public double getTotalCarbohydrates() {
		return totalCarbohydrates;
	}

	public void setTotalCarbohydrates(double totalCarbohydrates) {
		this.totalCarbohydrates = totalCarbohydrates;
	}

	public double getTotalProteins() {
		return totalProteins;
	}

	public void setTotalProteins(double totalProteins) {
		this.totalProteins = totalProteins;
	}

	public double getTotalFats() {
		return totalFats;
	}

	public void setTotalFats(double totalFats) {
		this.totalFats = totalFats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalCalories, totalCarbohydrates, totalFats, totalProteins);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NutritionalSummaryModel other = (NutritionalSummaryModel) obj;
		return Double.doubleToLongBits(totalCalories) == Double.doubleToLongBits(other.totalCalories)
				&& Double.doubleToLongBits(totalCarbohydrates) == Double.doubleToLongBits(other.totalCarbohydrates)
				&& Double.doubleToLongBits(totalFats) == Double.doubleToLongBits(other.totalFats)
				&& Double.doubleToLongBits(totalProteins) == Double.doubleToLongBits(other.totalProteins);
	}

	@Override
	public String toString() {
		return "NutritionalSummaryModel [totalCalories=" + totalCalories + ", totalCarbohydrates=" + totalCarbohydrates
				+ ", totalProteins=" + totalProteins + ", totalFats=" + totalFats + "]";
	}
    
    

}