package  com.janani.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janani.entity.QuestionCategory;
import com.janani.entity.SurveyQuestion;
import com.janani.entity.SurveyResponse;
import com.janani.model.SurveyQuestionsDTO;
import com.janani.repository.ImageReposiotry;
import com.janani.repository.QuestionCategoryRepository;
import com.janani.repository.SurveyQuestionRepository;
import com.janani.repository.SurveyResponseRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SurveyQuestionService implements ISurveyQuestionService {

	@Autowired
	private SurveyResponseRepository surveyResponseRepository;

	@Autowired
	private SurveyQuestionRepository surveyQuestionRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	@Autowired
	private ImageReposiotry imageRepository;

	@Override
	@Transactional
	public CompletableFuture<List<Object>> getAllQuestionsCategoryAsync(long rchId) {
		return CompletableFuture.supplyAsync(() -> {
			List<Object> quesCat = new ArrayList<>();
			Date currentDate = new Date();
			Date sevenDaysAgo = new Date(currentDate.getTime() - 7L * 24 * 60 * 60 * 1000);

			List<SurveyResponse> resultCatResp = surveyResponseRepository.findByRchId(rchId);

			if (resultCatResp.stream()
					.noneMatch(sr -> sr.getCreatedAt().compareTo(currentDate) == 0 && sr.getQuestionId() == 1)) {
				quesCat.add(
						questionCategoryRepository.findCategory1().stream().findFirst().orElse(new QuestionCategory()));
			}
			if (resultCatResp.stream()
					.noneMatch(sr -> sr.getCreatedAt().compareTo(currentDate) == 0 && sr.getQuestionId() == 2)) {
				quesCat.add(
						questionCategoryRepository.findCategory2().stream().findFirst().orElse(new QuestionCategory()));
			}
			if (!imageRepository.existsByRchIdAndClassificationAndCreatedAtBetween(rchId, "Health Survey Images",
					sevenDaysAgo, currentDate)) {
				quesCat.add(
						questionCategoryRepository.findCategory3().stream().findFirst().orElse(new QuestionCategory()));
			}

			return quesCat.isEmpty() ? null : quesCat;
		});
	}

	@Override
	@Transactional
	public CompletableFuture<List<SurveyQuestionsDTO>> getAllSurveyQuestionsAsync(long rchId, int catId) {
		return CompletableFuture.supplyAsync(() -> {
			List<SurveyQuestion> surveyQuestions = surveyQuestionRepository.findByCategoryId(catId);
			List<SurveyQuestionsDTO> surveyQuestionDTOs = new ArrayList<>();

			for (SurveyQuestion surveyQuestion : surveyQuestions) {
				SurveyQuestionsDTO dto = new SurveyQuestionsDTO();
				dto.setCategoryId(catId);
				dto.setQuestionId(surveyQuestion.getQuestionId());
				dto.setQuestionText(surveyQuestion.getQuestionText());
				dto.setQuestionType(surveyQuestion.getQuestionType());
				//dto.setQuestionOptions(surveyQuestion.getQuestionOptions());
				surveyQuestionDTOs.add(dto);
			}

			return surveyQuestionDTOs;
		});
	}

	@Override
	@Transactional
	public CompletableFuture<SurveyQuestion> addSurveyQuestionAsync(SurveyQuestion question) {
		return CompletableFuture.supplyAsync(() -> {
			SurveyQuestion savedQuestion = surveyQuestionRepository.save(question);
			return savedQuestion;
		});
	}

	@Override
	@Transactional
	public CompletableFuture<Void> updateSurveyQuestionAsync(SurveyQuestion question) {
		return CompletableFuture.runAsync(() -> {
			surveyQuestionRepository.save(question);
		});
	}
}
