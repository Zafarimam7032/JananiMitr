package com.janani.service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.janani.entity.SurveyQuestion;
import com.janani.model.SurveyQuestionsDTO;

public interface ISurveyQuestionService {
	
    CompletableFuture<List<Object>> getAllQuestionsCategoryAsync(long rchId);
    CompletableFuture<List<SurveyQuestionsDTO>> getAllSurveyQuestionsAsync(long rchId, int catId);
    CompletableFuture<SurveyQuestion> addSurveyQuestionAsync(SurveyQuestion question);
    CompletableFuture<Void> updateSurveyQuestionAsync(SurveyQuestion question);
}
