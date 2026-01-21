package com.strange.controller;

import com.strange.entities.User;
import com.strange.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeViewController {

    private final UserRepository userRepo;

    @GetMapping(path = "/")
    public String index(Model model, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            model.addAttribute("username", user.getUsername());
        }

        return "index";
    }

    @GetMapping(path = "/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @GetMapping(path = "/login")
    public String handleLogin(){
        return "signIn";
    }

}
