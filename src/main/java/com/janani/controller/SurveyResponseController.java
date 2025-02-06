package com.janani.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.janani.entity.FoodConsumption;
import com.janani.entity.SurveyResponse;
import com.janani.model.ImageDTO;
import com.janani.model.SurveyResponseDTO;
import com.janani.service.SurveyResponseService;

import java.util.List;

@RestController
@RequestMapping("/api/surveyresponse")
@PreAuthorize("hasRole('USER')")  // Authorization annotation
public class SurveyResponseController {

    private final SurveyResponseService surveyResponseService;
    private final Logger logger = LoggerFactory.getLogger(SurveyResponseController.class);

    @Autowired
    public SurveyResponseController(SurveyResponseService surveyResponseService) {
        this.surveyResponseService = surveyResponseService;
    }

    @GetMapping("/GetSurveyResponseById/{id}")
    public ResponseEntity<List<SurveyResponse>> getSurveyResponseById(@PathVariable int id) {
        try {
            List<SurveyResponse> surveyResponses = surveyResponseService.getSurveyResponsesByQuestionId(id);
            if (surveyResponses == null || surveyResponses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(surveyResponses);
        } catch (Exception ex) {
            logger.error("Error fetching survey response by ID", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/SaveSurveyResponse")
    public ResponseEntity<List<SurveyResponseDTO>> saveSurveyResponse(@RequestBody List<SurveyResponseDTO> surveyResponseDTOs) {
        try {
            if (surveyResponseDTOs == null || surveyResponseDTOs.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            surveyResponseService.saveSurveyResponse(surveyResponseDTOs);
            return ResponseEntity.ok(surveyResponseDTOs);
        } catch (Exception ex) {
            logger.error("Error saving survey response", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/UploadSurveyImages")
    public ResponseEntity<?> uploadSurveyImages(@RequestParam long RchId, @RequestParam List<MultipartFile> images) {
        try {
            if (RchId > 0 && !images.isEmpty()) {
                int result = surveyResponseService.uploadSurveyImages(RchId, images);
                if (result == 1) {
                    return ResponseEntity.ok(new Object() {
                        public final String message = "Images saved successfully";
                    });
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error while saving images to the server.");
                }
            }
            return ResponseEntity.badRequest().body("No files provided.");
        } catch (Exception ex) {
            logger.error("Error while uploading survey images", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while saving images to the server.");
        }
    }

    @PostMapping("/UploadAshaImage")
    public ResponseEntity<?> uploadAshaImage(@RequestParam long RchId, @RequestParam ImageDTO image) {
        try {
            if (RchId > 0 && image.getFormFile() != null) {
                int result = surveyResponseService.uploadAshaImage(RchId, image);
                if (result == 1) {
                    return ResponseEntity.ok(new Object() {
                        public final String message = "Image saved successfully";
                    });
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error while saving image to the server.");
                }
            }
            return ResponseEntity.badRequest().body("No file provided.");
        } catch (Exception ex) {
            logger.error("Error while uploading Asha image", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while saving image to the server.");
        }
    }

    @PostMapping("/CaptureFoodNitriens")
    public ResponseEntity<?> captureFoodNutrients(@RequestParam long RchId, @RequestParam MultipartFile formFile) {
        try {
            if (RchId > 0 && formFile != null && !formFile.isEmpty()) {
              FoodConsumption captureFoodNutrients = surveyResponseService.captureFoodNutrients(RchId, formFile);
                if (captureFoodNutrients != null) {
                    return ResponseEntity.ok(new Object() {
                        public final String message = "Processed image successfully.";
                    });
                } else {
                    return ResponseEntity.badRequest()
                            .body("Image file has issues, please upload a correct food image.");
                }
            }
            return ResponseEntity.badRequest().body("Image file has issues.");
        } catch (Exception ex) {
            logger.error("Error while capturing food nutrients", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while analyzing food nutrients, please upload only food-related images.");
        }
    }

    @GetMapping("/ProjectedAllSurveyDetails/{rchId}")
    public ResponseEntity<?> getProjectedAllSurveyDetails(@PathVariable long rchId) {
        try {
            var allSurveyResponse = surveyResponseService.projectedAllSurveyDetails(rchId);
            if (allSurveyResponse == null || allSurveyResponse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Object() { public final String message = "No data found for the given parameters."; });
            }
            return ResponseEntity.ok(allSurveyResponse);
        } catch (Exception ex) {
            logger.error("Error while getting survey details", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting survey responses of food and survey details.");
        }
    }

    @GetMapping("/GetTotalCaloriesAndRiskScore/{RchId}")
    public ResponseEntity<?> getTotalCaloriesAndRiskScore(@PathVariable long RchId) {
        try {
            if (RchId > 0) {
                var result = surveyResponseService.getTotalCaloriesAndRiskScore(RchId);
                if (result == null) {
                    return ResponseEntity.ok(new Object() {
                        public final String data = "";
                        public final String message = "No data found";
                    });
                }
                return ResponseEntity.ok(new Object() {
                    public final Object data = result;
                    public final String message = "Success";
                });
            }
            return ResponseEntity.badRequest().body("No file provided.");
        } catch (Exception ex) {
            logger.error("Error while calculating risk score", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while calculating risk score.");
        }
    }
}
