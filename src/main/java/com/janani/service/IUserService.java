package com.janani.service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.janani.entity.User;
import com.janani.model.UserViewModel;

public interface IUserService {
    CompletableFuture<Optional<User>> registration(UserViewModel viewModel);
    CompletableFuture<Optional<User>> getUserByIdAsync(long id);
    CompletableFuture<Optional<User>> getUserByMobNumberAsync(String mobNumber);
    CompletableFuture<Void> updateUserAsync(User user);
    CompletableFuture<Void> deleteUserAsync(long id);
    CompletableFuture<Boolean> userExists(String id);
    List<String> testPostgreSql();
}
