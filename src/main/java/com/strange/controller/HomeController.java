package com.strange.controller;

import com.strange.entities.User;
import com.strange.response.ApiResponse;
import com.strange.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserService userSer;

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> registerUser(
            @RequestPart("ur") User user,
            @RequestPart("userImage") MultipartFile userImg) {
        try {
            ApiResponse response = userSer.saveUser(user, userImg);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.builder()
                    .status(500)
                    .message("An error occurred: " + e.getMessage())
                    .build());
        }
    }
}