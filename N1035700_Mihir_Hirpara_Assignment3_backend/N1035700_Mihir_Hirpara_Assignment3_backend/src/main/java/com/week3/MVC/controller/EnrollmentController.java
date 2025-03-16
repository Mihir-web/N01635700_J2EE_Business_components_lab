package com.week3.MVC.controller;

import com.week3.MVC.entity.Enrollment;
import com.week3.MVC.entity.Program;
import com.week3.MVC.entity.User;
import com.week3.MVC.service.EnrollmentService;
import com.week3.MVC.service.ProgramService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final ProgramService programService;

    public EnrollmentController(EnrollmentService enrollmentService, ProgramService programService) {
        this.enrollmentService = enrollmentService;
        this.programService = programService;
    }

    // Get all programs a student is enrolled in
    @GetMapping
    public ResponseEntity<List<Enrollment>> getEnrolledPrograms(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        List<Enrollment> enrolledPrograms = enrollmentService.getEnrolledPrograms(user.getId());
        return ResponseEntity.ok(enrolledPrograms);
    }

    // Get all available programs
    @GetMapping("/programs")
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    // Enroll in a new program
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollInProgram(HttpSession session, @RequestParam String programCode, @RequestParam Double amountPaid) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String message = enrollmentService.enrollInProgram(user.getId(), programCode, amountPaid);
        return ResponseEntity.ok(message);
    }

    // Drop an enrolled program
    @DeleteMapping("/drop/{id}")
    public ResponseEntity<String> dropProgram(HttpSession session, @PathVariable long id) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String message = enrollmentService.dropProgram(user.getId(), id);
        return ResponseEntity.ok(message);
    }
}
