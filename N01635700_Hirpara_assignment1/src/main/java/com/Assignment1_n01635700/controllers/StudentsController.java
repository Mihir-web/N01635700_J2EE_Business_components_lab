package com.Assignment1_n01635700.controllers;

import com.Assignment1_n01635700.entities.Students;
import com.Assignment1_n01635700.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/students")
    public String viewAllStudents(Model model) {
        model.addAttribute("students", studentsService.getAllStudents().values());
        return "Students/listing";
    }

    @GetMapping("/addStudent")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Students(0, "", 0, "", "", "", LocalDate.now()));
        return "Students/addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam("id") int id, @RequestParam("name") String name,
                             @RequestParam("age") int age, @RequestParam("gender") String gender,
                             @RequestParam("email") String email, @RequestParam("city") String city,
                             @RequestParam("dob") String dob) {
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Students newStudent = new Students(id, name, age, gender, email, city, dateOfBirth);
        studentsService.addStudent(newStudent);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentForm(@PathVariable int id, Model model) {
        Students student = studentsService.getStudentById(id);
        model.addAttribute("student", student);
        return "Students/updateStudent"; // Thymeleaf form to update student
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam("id") int id, @RequestParam("name") String name,
                                @RequestParam("age") int age, @RequestParam("gender") String gender,
                                @RequestParam("email") String email, @RequestParam("city") String city,
                                @RequestParam("dob") String dob) {
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Students updatedStudent = new Students(id, name, age, gender, email, city, dateOfBirth);
        studentsService.updateStudent(updatedStudent);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentsService.deleteStudent(id);
        return "redirect:/students";
    }
}