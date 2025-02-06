package com.janani.service;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.janani.entity.User;
import com.janani.model.OTPModel;
import com.janani.model.ValidateOTPModel;

public interface ILoginService {
    CompletableFuture<Optional<User>> getLogInById(String id);
    CompletableFuture<Void> addOrUpdateAuthUser(int userId, String tokenId);
    CompletableFuture<String> getOTPForAuthenticate(OTPModel otpModel);
    CompletableFuture<String> validateOTP(ValidateOTPModel valOtpModel);
}
