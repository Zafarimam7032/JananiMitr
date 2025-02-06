package com.janani.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.janani.entity.FoodConsumption;
import com.janani.entity.Image;
import com.janani.entity.SurveyResponse;
import com.janani.model.ImageDTO;
import com.janani.model.NutritionalSummaryModel;
import com.janani.model.SurveyResponseDTO;
import com.janani.model.SurveyResponseProjected;
import com.janani.repository.CumulativeRiskScoreReposiotry;
import com.janani.repository.FoodConsumptionRepository;
import com.janani.repository.ImageReposiotry;
import com.janani.repository.SurveyResponseRepository;

import jakarta.transaction.Transactional;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class SurveyResponseService implements ISurveyResponseService {

	private static final Logger logger = LoggerFactory.getLogger(SurveyResponseService.class);

	private final String bucketName = "aigmobiledata"; // Bucket name
	private final String region = "ap-south-1"; // AWS region

	@Value("${nutriemother.filepaths.surveyImageUploadPath}")
	private String surveyImageUploadPath;

	@Value("${nutriemother.filepaths.ashaImageUploadPath}")
	private String ashaImageUploadPath;

	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.s3.region}")
	private String s3Region;

	@Autowired
	private ImageReposiotry imageReposiotry;

	@Autowired
	private SurveyResponseRepository surveyResponseRepository;

	@Autowired
	private CommonService commonService;

	@Autowired
	private FoodConsumptionRepository foodConsumptionRepository;

	@Autowired
	private CumulativeRiskScoreReposiotry cumulativeRiskScoreReposiotry;
	
    @Autowired
    private S3Client s3Client;

	@Override
	public List<SurveyResponse> getSurveyResponsesByQuestionId(int questionId) {
		return surveyResponseRepository.findSurveyResponsesByQuestionId(questionId);
	}

	@Override
	public List<SurveyResponse> saveSurveyResponse(List<SurveyResponseDTO> surveyResponsesDTO) {
		List<SurveyResponse> responses = new ArrayList<>();

		if (surveyResponsesDTO != null && !surveyResponsesDTO.isEmpty()) {
			for (SurveyResponseDTO dto : surveyResponsesDTO) {
				if (dto.getQuestionOptions().size() == 1) {
					SurveyResponse surveyResponse = new SurveyResponse();
					surveyResponse.setRchId(dto.getRchId());
					surveyResponse.setQuestionId(dto.getQuestionId());
					surveyResponse.setOptionId(dto.getQuestionOptions().get(0).getOptionId());
					surveyResponse.setResponseText(String.valueOf(dto.getRchId()));
					responses.add(surveyResponse);
				} else if (dto.getQuestionOptions().size() > 1) {
					for (var option : dto.getQuestionOptions()) {
						SurveyResponse surveyResponse = new SurveyResponse();
						surveyResponse.setRchId(dto.getRchId());
						surveyResponse.setQuestionId(dto.getQuestionId());
						surveyResponse.setOptionId(option.getOptionId());
						surveyResponse.setResponseText(String.valueOf(dto.getRchId()));
						responses.add(surveyResponse);
					}
				}
			}
			surveyResponseRepository.saveAll(responses);
		}
		return responses;
	}

	@Override
	public int uploadSurveyImages(long rchId, List<MultipartFile> images) {
		BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKey,
				awsSecretKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();

		String folderPath = String.format(surveyImageUploadPath, rchId, java.time.LocalDate.now().toString());
		List<Image> imageList = new ArrayList<>();

		for (MultipartFile file : images) {
			if (!file.isEmpty()) {
				try {
					String customFileName = String.format("%s_%s", java.time.LocalDateTime.now().toString(),
							file.getOriginalFilename());
					String filePath = folderPath + "/" + customFileName;
					TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
					transferManager.upload(bucketName, filePath, file.getInputStream(), null).waitForCompletion();

					GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, filePath)
							.withMethod(HttpMethod.GET)
							.withExpiration(java.util.Date.from(java.time.Instant.now().plusSeconds(60)));

					URL imageUrl = s3Client.generatePresignedUrl(request);
					String beforeQuestionMarkURL = imageUrl != null ? imageUrl.toString().split("\\?")[0] : "";

					Image image = new Image();
					
					image.setRchId(rchId);
					image.setLatitude(0.0);
					image.setLongitude(0.0);
					image.setPhotoName(customFileName);
					image.setPhotoPath(beforeQuestionMarkURL);
					image.setClassification("AshaImage");
					image.setCreatedBy(String.valueOf(rchId));
					image.setIsAshaImage(true);
					imageList.add(image);

				} catch (IOException e) {
					logger.error("Error encountered on server. Message: '{}'", e.getMessage());
					return -1;
				} catch (Exception e) {
					logger.error("Unknown error occurred. Message: '{}'", e.getMessage());
					return -1;
				}
			}
		}

		if (!imageList.isEmpty()) {
			imageReposiotry.saveAll(imageList);
			return 1;
		}
		return 0;
	}

	@Override
	public int uploadAshaImage(long rchId, ImageDTO imageDTO) {
		String folderPath = String.format(ashaImageUploadPath, rchId, java.time.LocalDate.now().toString());
		Image image = new Image();
		int imageSave = 0;

		if (imageDTO.getFormFile() != null && !imageDTO.getFormFile().isEmpty()) {
			try {
				String customFileName = String.format("%s_%s", java.time.LocalDateTime.now().toString(),
						imageDTO.getFormFile().getOriginalFilename());
				String filePath = folderPath + "/" + customFileName;
				BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKey,
						awsSecretKey);
				AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
						.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();

				TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
				transferManager.upload(bucketName, filePath, imageDTO.getFormFile().getInputStream(), null)
						.waitForCompletion();

				GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, filePath)
						.withMethod(HttpMethod.GET)
						.withExpiration(java.util.Date.from(java.time.Instant.now().plusSeconds(60)));

				URL imageUrl = s3Client.generatePresignedUrl(request);
				String beforeQuestionMarkURL = imageUrl != null ? imageUrl.toString().split("\\?")[0] : "";
				
				image.setRchId(rchId);
				image.setLatitude(imageDTO.getLatitude());
				image.setLongitude(imageDTO.getLongitude());
				image.setPhotoName(customFileName);
				image.setPhotoPath(beforeQuestionMarkURL);
				image.setClassification("AshaImage");
				image.setCreatedBy(String.valueOf(rchId));
				image.setIsAshaImage(true);
				
				
				imageSave = 1;

			} catch (IOException e) {
				logger.error("Error encountered on server. Message: '{}'", e.getMessage());
				return -1;
			} catch (Exception e) {
				logger.error("Unknown error occurred. Message: '{}'", e.getMessage());
				return -1;
			}

			imageReposiotry.save(image); // Save image to DB
		}
		return imageSave;
	}

	@Transactional
	public void updateSurveyResponseAsync(SurveyResponse response) {
		surveyResponseRepository.save(response);
	}

	@Transactional
	public void deleteSurveyResponseAsync(int responseId) {
		Optional<SurveyResponse> response = surveyResponseRepository.findById(responseId);
		if (response.isPresent()) {
			surveyResponseRepository.deleteById(responseId);
		}
	}

	public double calculateRiskScore(long rchId, int categoryId) {
		double calculateRiskScore = surveyResponseRepository.calculateRiskScore(rchId, new Date(), categoryId);
		return calculateRiskScore;
	}

	public FoodConsumption captureFoodNutrients(long rchId, MultipartFile image) throws Exception, ExecutionException {
		String folderPath = String.format(surveyImageUploadPath, rchId, new Date().toString());
		String customFileName = String.format("%s_%s", new Date().toString(), image.getOriginalFilename());
		String filePath = folderPath + "/" + customFileName;
		String imageUrl = null;

		try (InputStream stream = image.getInputStream()) {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(filePath).build();
			s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(stream, image.getSize()));

			GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(filePath).build();
			 ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);
			imageUrl = response.toString(); // process S3 response URL

		} catch (Exception e) {
			logger.error("Error while uploading image", e);
		}

		String responseBody = commonService.callNutritionApi(imageUrl).get();
		if (responseBody != null && !responseBody.isEmpty()) {
			FoodConsumption foodConsumption = new FoodConsumption();
			foodConsumption.setRchId(rchId);
//			foodConsumption.setPhotoPath(imageUrl);
//			foodConsumption.setPhotoName(customFileName);
//			foodConsumption.setCreatedBy(String.valueOf(rchId));

			foodConsumptionRepository.save(foodConsumption);
			foodConsumptionRepository.callSaveDishFunction(rchId, responseBody);

		}

		return null;
	}

	public Map<String, String> getTotalCaloriesAndRiskScore(long rchId) {

		Double totalCalories = foodConsumptionRepository.findTotalCaloriesByRchIdAndCurrentDate(rchId);
		List<Map<String, Object>> cumulativeRiskScores = cumulativeRiskScoreReposiotry
				.findCumulativeRiskScoresByRchIdAndCurrentDate(rchId);
		Map<String, String> riskScore = new HashMap<>();
		String totalRisk = "Low";

		for (String category : Arrays.asList("Food", "Medicine")) {
			Optional<Map<String, Object>> categoryRisk = cumulativeRiskScores.stream()
					.filter(cr -> cr.get("category").equals(category)).findFirst();
			if (categoryRisk.isPresent()) {
				String riskValue = (String) categoryRisk.get().get("final_risk_value");
				riskScore.put(category, riskValue);

				if ("High".equals(riskValue)) {
					totalRisk = "High";
				} else if ("Medium".equals(riskValue) && !"High".equals(totalRisk)) {
					totalRisk = "Medium";
				}
			}
		}

		riskScore.put("TotalRisk", totalRisk);
		if (totalCalories != null) {
			riskScore.put("TotalCalories", totalCalories.toString());
		}

		return riskScore;
	}

	public Map<String, Object> projectedAllSurveyDetails(long rchId) {
		List<SurveyResponseProjected> surveyResponses = new ArrayList<>();
		List<NutritionalSummaryModel> foodNutrients = new ArrayList<>();
		Map<String, Object> foodSurveyRsp = new HashMap<>();
		Date currentDate = new Date();

		List<Object[]> results = surveyResponseRepository.getAllSurveyResponses(rchId, currentDate);

		for (Object[] result : results) {
			int rchIdResult = (int) result[0];
			String categoryName = (String) result[1];
			String questionText = (String) result[2];
			String optionText = (String) result[3];
			String createdAt = (String) result[4];

			surveyResponses
					.add(new SurveyResponseProjected(rchIdResult, categoryName, questionText, optionText, createdAt));
		}

		NutritionalSummaryModel nutritionalSummaryModel = foodConsumptionRepository.getNutritionalSummaryModel(rchId,
				new Date());
		if (nutritionalSummaryModel != null) {
			foodNutrients.add(nutritionalSummaryModel);
			if (!foodNutrients.isEmpty()) {
				NutritionalSummaryModel lastItem = foodNutrients.get(foodNutrients.size() - 1);
				if (lastItem.getTotalCalories() == 0.0 && lastItem.getTotalCarbohydrates() == 0.0
						&& lastItem.getTotalProteins() == 0.0 && lastItem.getTotalFats() == 0.0) {
					foodNutrients.clear(); // Clear the list if all values are 0.0
				}
			}

			// Add to response map
			foodSurveyRsp.put("SurveyResponse", surveyResponses);
			foodSurveyRsp.put("FoodResponse", foodNutrients);

			if (!foodSurveyRsp.isEmpty()) {
				return foodSurveyRsp;
			}
		}
		return null;
	}

	public Object riskScoreByCatId(long rchId, int categoryId) {
		// categoryId = 0 ALL
		// categoryId = 1 FOOD
		// categoryId = 2 MEDICINE
		// categoryId = 3 SYMPTOMS

		return null;
	}
}
