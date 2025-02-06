package com.janani.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.janani.model.ErrorResponse;
import com.janani.model.GeminiRequest;
import com.janani.service.IGeminiService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

	@Autowired
    private IGeminiService geminiService;

    @PostMapping("/getAIAnswer")
    public CompletableFuture<ResponseEntity<?>> getAnswer(@RequestBody GeminiRequest request) {
        // Validate the request
        if (request == null) {
            return CompletableFuture.completedFuture(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Request body cannot be null."))
            );
        }

        if (request.getQuery() == null || request.getQuery().isEmpty()) {
            return CompletableFuture.completedFuture(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Query is required and cannot be empty."))
            );
        }

        if (request.getLanguage() == null || request.getLanguage().isEmpty()) {
            return CompletableFuture.completedFuture(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Language is required and cannot be empty."))
            );
        }

        return geminiService.getAnswerAsync(request.getQuery(), request.getLanguage())
            .thenApply(answer -> {
                if (answer == null || answer.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ErrorResponse("No answer was generated for the provided query."));
                }
                return ResponseEntity.ok(answer);
            })
            .exceptionally(ex -> 
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("An error occurred while processing the request."+ ex.getMessage()))
            );
    }
}
