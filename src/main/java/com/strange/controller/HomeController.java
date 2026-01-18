package com.strange.controller;

import com.strange.entities.User;
import com.strange.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private UserRepository userRepo;

//    private PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User ur) {
//        ur.setPassword(passwordEncoder.encode(ur.getPassword()));
        ur.setUserRole("ROLE_USER");

        userRepo.save(ur);

        return "redirect:/login";
    }
}
