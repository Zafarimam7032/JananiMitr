package com.janani.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.janani.model.SurveyQuestionsDTO;
import com.janani.service.ISurveyQuestionService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/survey-question")
@PreAuthorize("hasRole('USER')") // For authorization, assuming you are using Spring Security
public class SurveyQuestionController {

    @Autowired
    private  ISurveyQuestionService surveyQuestionService;


    // Get all question categories
    @GetMapping("/GetAllQuestionCategory")
    public ResponseEntity<?> getAllQuestionCategory(@RequestParam("RchId") long RchId) {
        try {
            if (RchId <= 0) {
                return new ResponseEntity<>("User Id Required", HttpStatus.BAD_REQUEST);
            }

             CompletableFuture<List<Object>> questionsCategoryAsync = surveyQuestionService.getAllQuestionsCategoryAsync(RchId);
             List<Object> questionCategories = questionsCategoryAsync.get();
            if (questionCategories == null || questionCategories.isEmpty()) {
                return new ResponseEntity<>("Already User Filled the Surveys", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(questionCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error While Fetching Questions Categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all survey questions by category and user ID
    @GetMapping("/GetAllSurveyQuestions/{RchId}/{catId}")
    public ResponseEntity<?> getAllSurveyQuestions(@PathVariable("RchId") long RchId, @PathVariable("catId") int catId) {
        try {
            if (catId > 0 && RchId > 0) {
                CompletableFuture<List<SurveyQuestionsDTO>> allSurveyQuestionsAsync = surveyQuestionService.getAllSurveyQuestionsAsync(RchId, catId);

                List<SurveyQuestionsDTO> surveyQuestions = allSurveyQuestionsAsync.get();
                
                if (surveyQuestions == null || surveyQuestions.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(surveyQuestions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error while Fetching Survey Questions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This endpoint can be used to add a new survey question, similar to the commented code in the original C#
    // Uncomment and implement according to your service
    /*
    @PostMapping("/AddSurveyQuestion")
    public ResponseEntity<?> addSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion) {
        try {
            if (surveyQuestion == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            SurveyQuestion createdQuestion = surveyQuestionService.addSurveyQuestionAsync(surveyQuestion);
            return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating new Survey Question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    
    // This endpoint can be used to update an existing survey question, similar to the commented code in the original C#
    // Uncomment and implement according to your service
    /*
    @PostMapping("/UpdateSurveyQuestion")
    public ResponseEntity<?> updateSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion) {
        try {
            if (surveyQuestion == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            SurveyQuestion updatedQuestion = surveyQuestionService.updateSurveyQuestionAsync(surveyQuestion);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating Survey Question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    
    // This endpoint can be used to delete a survey question, similar to the commented code in the original C#
    // Uncomment and implement according to your service
    /*
    @DeleteMapping("/DeleteSurveyQuestionById/{id}")
    public ResponseEntity<?> deleteSurveyQuestion(@PathVariable("id") long id) {
        try {
            if (id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            surveyQuestionService.deleteSurveyQuestionAsync(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Survey Question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
}