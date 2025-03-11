package com.week3.MVC.controller;


import com.week3.MVC.entity.Enrollment;
import com.week3.MVC.entity.Program;
import com.week3.MVC.entity.User;
import com.week3.MVC.service.EnrollmentService;
import com.week3.MVC.service.ProgramService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final ProgramService programService;

    public EnrollmentController(EnrollmentService enrollmentService, ProgramService programService) {
        this.enrollmentService = enrollmentService;
        this.programService = programService; // Initialize programService
    }

    // Display all programs the student is enrolled in
    @GetMapping("/")
    public String getEnrolledPrograms(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser"); // Retrieve studentId from session
        if (user == null) {
            return "redirect:/"; // Redirect to login page if student is not logged in
        }

        List<Enrollment> enrolledPrograms = enrollmentService.getEnrolledPrograms(user.getId());
        model.addAttribute("enrolledPrograms", enrolledPrograms);
        return "enrollment/enrolled-programs"; // View for showing enrolled programs
    }

    @GetMapping("/add_program")
    public String addProgram(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/";
        }

        // Fetch enrolled programs
        List<Enrollment> enrolledPrograms = enrollmentService.getEnrolledPrograms(user.getId());
        model.addAttribute("enrolledPrograms", enrolledPrograms);

        // Fetch all available programs
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);

        return "enrollment/add-program"; // View for adding programs
    }

    // Enroll in a new program
    @PostMapping("/enroll")
    public String enrollInProgram(HttpSession session, @RequestParam String programCode, @RequestParam Double amountPaid, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/"; // Redirect to login page if student is not logged in
        }

        String message = enrollmentService.enrollInProgram(user.getId(), programCode, amountPaid);
        model.addAttribute("message", message);
        return "redirect:/enrollments/"; // Redirect back to the enrolled programs page
    }

    // Drop an enrolled program
    @PostMapping("/drop")
    public String dropProgram(HttpSession session, @RequestParam long id, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/"; // Redirect to login page if student is not logged in
        }

        String message = enrollmentService.dropProgram(user.getId(), id);  // Pass the id (enrollment record ID)
        model.addAttribute("message", message);
        return "redirect:/enrollments/"; // Redirect back to the enrolled programs page
    }

}

