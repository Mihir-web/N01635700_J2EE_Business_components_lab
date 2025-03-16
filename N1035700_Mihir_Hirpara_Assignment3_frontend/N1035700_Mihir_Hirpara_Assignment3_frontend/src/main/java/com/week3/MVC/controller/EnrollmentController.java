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

    // Display all programs the student is enrolled in
    @GetMapping("/")
    public String getEnrolledPrograms(HttpSession session, Model model) {
        return "enrollment/enrolled-programs"; // View for showing enrolled programs
    }

    @GetMapping("/add_program")
    public String addProgram(HttpSession session, Model model) {
        return "enrollment/add-program"; // View for adding programs
    }
}

