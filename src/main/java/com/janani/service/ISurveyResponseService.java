package com.janani.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.janani.entity.SurveyResponse;
import com.janani.model.ImageDTO;
import com.janani.model.SurveyResponseDTO;

public interface ISurveyResponseService {

	public List<SurveyResponse> getSurveyResponsesByQuestionId(int questionId);
	
	public List<SurveyResponse> saveSurveyResponse(List<SurveyResponseDTO> surveyResponsesDTO);
	
	public int uploadSurveyImages(long rchId, List<MultipartFile> images);
	
	public int uploadAshaImage(long rchId, ImageDTO imageDTO);
}
