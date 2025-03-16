package com.week3.MVC.controller;

import com.week3.MVC.entity.User;
import com.week3.MVC.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model, HttpSession session) {
        return "students-list"; // Return the correct template name
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        return "add-user";
    }


    @GetMapping("/welcome")
    public String welcomePage(Model model, HttpSession session) {
            return "welcome";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        return "edit";
    }


    @GetMapping("/restricted")
    public String restrictedPage(HttpSession session) {
            return "restricted";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate session to log out
        return "redirect:/"; // Redirect to the home page
    }
}
