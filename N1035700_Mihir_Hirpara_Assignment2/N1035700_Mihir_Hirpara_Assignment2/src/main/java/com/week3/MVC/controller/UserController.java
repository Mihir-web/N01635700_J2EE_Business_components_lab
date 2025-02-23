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
        // Ensure only admin users can access this page
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getIsAdmin() != 1) {
            return "redirect:/"; // Redirect non-admin users
        }

        // Fix: Ensure `getAllNonAdminUsers()` returns a List<User>
        List<User> students = userService.getAllNonAdminUsers();

        if (students.isEmpty()) {
            model.addAttribute("message", "No students registered.");
        } else {
            model.addAttribute("students", students); // Correctly add students list to model
        }


        return "students-list"; // Return the correct template name
    }



    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/signup")
    public String addNewUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user"; // If validation fails, return to the add-user page
        }

        logger.info("Received request to add student: {}", user.getUserName());
        userService.addUser(user);
        model.addAttribute("response", "added");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session) {
        logger.info("Received request to login for user: {}", userName);
        User user = userService.userLogin(userName, password);
        if (!Objects.isNull(user)) {
            logger.info("User {}, logged in successfully", userName);
            session.setAttribute("loggedInUser", user);
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            return "welcome";
        } else {
            logger.warn("Authentication failed for the user: {}", userName);
            model.addAttribute("loginError", "Invalid username or password.");
            return "index"; // Show login error message
        }
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            return "welcome";
        } else {
            return "redirect:/"; // Redirect to login page if not logged in
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userService.findByStudentId(id);
        if (user == null) {
            model.addAttribute("error", "Student not found");
            return "redirect:/"; // Redirect if student not found
        }
        model.addAttribute("user", user); // Ensure consistency in attribute name
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "edit"; // If validation fails, return to the edit form with errors
        }

        // Fetch the existing student from the database to retain old data
        User existingUser = userService.findByStudentId(id);
        if (existingUser == null) {
            model.addAttribute("error", "User not found");
            return "edit";
        }

        // If password is not provided, retain the old password
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword()); // Keep the old password
        }

        user.setId(id);
        userService.addUser(user); // Use updateUser instead of addUser

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getIsAdmin() == 1) {
            return "redirect:/students"; // Redirect to the welcome page after successful update
        }else{
            session.setAttribute("loggedInUser", user);
            return "redirect:/welcome";
        }


    }

    @GetMapping("/restricted")
    public String restrictedPage(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return "restricted";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate session to log out
        return "redirect:/"; // Redirect to the home page
    }

    @PostMapping("/students/delete/{id}")
    public String softDeleteUser(@PathVariable Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getIsAdmin() != 1) {
            return "redirect:/"; // Restrict access to admins only
        }

        userService.softDeleteUser(id); // Call the soft delete method
        return "redirect:/students"; // Redirect back to the student list
    }

}
