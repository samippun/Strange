package com.strange.serviceImpl;

import com.strange.entities.User;
import com.strange.repositories.UserRepository;
import com.strange.response.ApiResponse;
import com.strange.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse saveUser(User ur, MultipartFile userImg) throws Exception{
        // Validate file
        if (userImg.isEmpty()) {
            throw new IllegalArgumentException("User image cannot be empty");
        }

        ur.setPassword(passwordEncoder.encode(ur.getPassword()));
        ur.setUserRole("ROLE_USER");
        ur.setUserImage(userImg.getOriginalFilename());

        userRepo.save(ur);

        // Save the user image
        String uploadDir = "static/images";
        File saveFile = new File(uploadDir);

        // Create directory if it doesn't exist
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        // Save the file
        Path path = Paths.get(uploadDir + File.separator + userImg.getOriginalFilename());
        Files.copy(userImg.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return ApiResponse.builder()
                .status(200)
                .message("User successfully registered")
                .build();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public long countUsers() {
        return userRepo.countUsersByRole();
    }

    @Override
    public List<User> getUserByRole() {
        return userRepo.findAllUsersByUserRole();
    }

    @Override
    public void deleteUserById(int userID) {
        userRepo.deleteById(userID);
    }
}