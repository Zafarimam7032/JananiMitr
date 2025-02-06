package com.janani.service;
import java.util.concurrent.CompletableFuture;

public interface IGeminiService {
    CompletableFuture<String> getAnswerAsync(String query, String language);
}
