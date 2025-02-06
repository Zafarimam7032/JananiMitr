package com.janani.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.janani.entity.FoodConsumption;
import com.janani.model.NutritionalSummaryModel;

import jakarta.transaction.Transactional;

@Repository
public interface FoodConsumptionRepository extends JpaRepository<FoodConsumption, Integer> {

	@Query(value = "SELECT total_calories,total_carbohydrates,total_proteins,total_fats FROM get_nutritional_summary(:rchId, :date)", nativeQuery = true)
	public NutritionalSummaryModel getNutritionalSummaryModel(@Param("rchId") long rchId, @Param("date") Date date);

	  @Query("SELECT SUM(fc.calories) FROM FoodConsumption fc WHERE fc.rchId = :rchId AND fc.consumptionDate = CURRENT_DATE")
	    Double findTotalCaloriesByRchIdAndCurrentDate(@Param("rchId") long rchId);
	  
	   @Modifying
	    @Transactional
	    @Query(value = "SELECT save_dish(:rchId, :responseBody)", nativeQuery = true)
	    void callSaveDishFunction(@Param("rchId") long rchId, @Param("responseBody") String responseBody);
}
