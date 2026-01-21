package com.strange.controller;

import com.strange.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminViewController {

    private final UserService userSer;

    @GetMapping(path = "dashboard")
    public String adminDashboard(Model model) {

        long userCount = userSer.countUsers();
        model.addAttribute("userCount", userCount);

        return "/admin/dashboard";
    }

    @GetMapping("/userDetails")
    public String getUsers(Model model) {
        model.addAttribute("users", userSer.getUserByRole());
        return "admin/userDetails";
    }

    @GetMapping("/user/delete/{userID}")
    public String deleteUser(@PathVariable("userID") int userID){
        userSer.deleteUserById(userID);
        return "redirect:/admin/userDetails";
    }
}
