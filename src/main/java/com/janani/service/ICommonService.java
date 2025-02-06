package com.janani.service;
import java.util.concurrent.CompletableFuture;

public interface ICommonService {
    boolean isAuthUser(long rchId, String tokenId);
    CompletableFuture<String> callNutritionApi(String imageURL);
}
