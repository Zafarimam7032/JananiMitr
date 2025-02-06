package com.janani.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.janani.entity.User;
import com.janani.model.UserViewModel;
import com.janani.repository.UserRepository;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Override
    public CompletableFuture<Optional<User>> registration(UserViewModel viewModel) {
        return CompletableFuture.supplyAsync(() -> {
            boolean userExists = userRepository.findAll().stream()
                    .anyMatch(user -> user.getRchId() == viewModel.getRchId());

            if (userExists) {
                return Optional.empty();
            }

            User user = new User();
            user.setRchId(viewModel.getRchId());
            user.setName(viewModel.getName());
            user.setMobileNumber(viewModel.getMobileNumber());
            user.setAlternativeMobileNumber(viewModel.getAlternativeMobileNumber());
            user.setDateOfBirth(viewModel.getDateOfBirth());
            user.setHeight((viewModel.getHeight() != null ? viewModel.getHeight() : 0) * 30.48 + (viewModel.getHeightIn() != null ? viewModel.getHeightIn() : 0) * 2.54);
            user.setWeight(viewModel.getWeight());
            user.setLmpDate(viewModel.getLmpDate());
            user.setVillage(viewModel.getVillage());
            user.setStatus(true);
            user.setCreatedBy(user.getName());
            user.setModifiedBy(null);
            user.setState("");
            user.setDistrict("");
            user.setMandal("");
            user.setPincode("");
            
            userRepository.findAll().add(user);
            userRepository.save(user);

            insertConsentRecord(viewModel.getRchId(), true);

            return Optional.of(user);
        });
    }

    @Override
    public CompletableFuture<Optional<User>> getUserByIdAsync(long id) {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll().stream()
                .filter(user -> user.getRchId() == id)
                .findFirst());
    }

    @Override
    public CompletableFuture<Optional<User>> getUserByMobNumberAsync(String mobNumber) {
        return CompletableFuture.supplyAsync(() -> {
            boolean userExists = userExists(mobNumber).join();

            if (userExists) {
                return userRepository.findAll().stream()
                        .filter(user -> user.getMobileNumber().equals(mobNumber) || user.getAlternativeMobileNumber().equals(mobNumber))
                        .findFirst();
            }

            return Optional.empty();
        });
    }

    @Override
    @Transactional
    public CompletableFuture<Void> updateUserAsync(User user) {
        return CompletableFuture.runAsync(() -> {
        	userRepository.findAll().removeIf(u -> u.getRchId() == user.getRchId());
        	userRepository.findAll().add(user);
        	userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteUserAsync(long id) {
        return CompletableFuture.runAsync(() -> {
        	userRepository.findAll().removeIf(user -> user.getRchId() == id);
        	userRepository.deleteById(id);
        });
    }

    @Override
    public CompletableFuture<Boolean> userExists(String id) {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll().stream()
                .anyMatch(user -> user.getMobileNumber().equals(id) || user.getAlternativeMobileNumber().equals(id)));
    }

    private void insertConsentRecord(long rchId, boolean consent) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT insert_consent_record(?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, rchId);
                statement.setBoolean(2, consent);

                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting consent record", e);
        }
    }

    public List<String> testPostgreSql() {
        List<String> data = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT rchid, mobile_number FROM public.user";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    data.add(String.format("mobile_number: %s, rchid: %d",
                            resultSet.getString("mobile_number"),
                            resultSet.getLong("rchid")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error testing PostgreSQL connection", e);
        }

        return data;
    }
}
