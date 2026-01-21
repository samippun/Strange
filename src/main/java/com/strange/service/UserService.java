package com.strange.service;

import com.strange.entities.User;
import com.strange.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ApiResponse saveUser(User ur, MultipartFile userImg) throws Exception;

    Optional<User> getUserByUsername(String username);

    long countUsers();

    List<User> getUserByRole();

    void deleteUserById(int userID);
}
