package com.week3.MVC.controller;

import com.week3.MVC.entity.User;
import com.week3.MVC.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getIsAdmin() != 1) {
            return ResponseEntity.status(403).body("Access denied");
        }

        List<User> students = userService.getAllNonAdminUsers();
        if (students.isEmpty()) {
            return ResponseEntity.ok("No students registered.");
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User user) {
        logger.info("Received request to add student: {}", user.getUserName());
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User loginRequest, HttpSession session) {
        logger.info("Received request to login for user: {}", loginRequest.getUserName());
        User user = userService.userLogin(loginRequest.getUserName(), loginRequest.getPassword());

        if (!Objects.isNull(user)) {
            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/welcome")
    public ResponseEntity<?> welcomePage(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user, HttpSession session) {
        User existingUser = userService.findByStudentId(id);
        if (existingUser == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        }

        user.setId(id);
        userService.addUser(user);
        session.setAttribute("loggedInUser", user);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<?> softDeleteUser(@PathVariable Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getIsAdmin() != 1) {
            return ResponseEntity.status(403).body("Access denied");
        }

        userService.softDeleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
