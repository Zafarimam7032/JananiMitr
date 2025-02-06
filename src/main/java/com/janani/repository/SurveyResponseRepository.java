package com.janani.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.janani.entity.SurveyResponse;

@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Integer> {

    @Query(value = "SELECT rch_id, category_name, question_text, option_text, created_at FROM get_all_survey_responses(:rchId, :currentDate)", nativeQuery = true)
    List<Object[]> getAllSurveyResponses(@Param("rchId") long rchId, @Param("currentDate") Date currentDate);
	
    // Custom method using method naming conventions
    List<SurveyResponse> findByQuestionId(int questionId);
    
    // Alternatively, using the @Query annotation to customize the query
    @Query("SELECT sr FROM SurveyResponse sr WHERE sr.questionId = :questionId")
    List<SurveyResponse> findSurveyResponsesByQuestionId(@Param("questionId") int questionId);
    
    List<SurveyResponse> findByRchId(Long rchId);
    
    @Query(value = "SELECT calculated_risc_score(:rchId, :currentDate, :categoryId)", nativeQuery = true)
    double calculateRiskScore(@Param("rchId") long rchId, @Param("currentDate") Date currentDate, @Param("categoryId") int categoryId);

}
