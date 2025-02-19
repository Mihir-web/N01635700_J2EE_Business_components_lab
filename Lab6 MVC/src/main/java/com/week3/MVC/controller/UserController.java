package com.week3.MVC.controller;



import com.week3.MVC.entity.User;
import com.week3.MVC.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

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

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/signup")
    public String addNewUser(@ModelAttribute User user, Model model) {
        logger.info("Received request to add user: {}", user.getUserName());
        userService.addUser(user);
        model.addAttribute("response", "added");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String userName, @RequestParam String password, Model model) {
        logger.info("Received request to login for user: {}", userName);
        User user = userService.userLogin(userName, password);
        if (!Objects.isNull(user)) {
            logger.info("User {}, logged in successfully", userName);
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            return "welcome";
        } else {
            logger.info("Authentication failed for the user: {}", userName);
            return "index";
        }


    }
}

