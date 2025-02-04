package com.week3.MVC.controller;

import com.week3.MVC.entity.Employee;
import com.week3.MVC.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(@RequestParam(required = false, name = "search") String search, Model model) {
        if (search != null) {
            try {
                Long id = Long.parseLong(search);
                Employee employee = employeeService.getEmployeeById(id);
                model.addAttribute("employees", employee != null ? List.of(employee) : List.of());
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid ID format");
                model.addAttribute("employees", employeeService.getAllEmployees());
            }
        } else {
            model.addAttribute("employees", employeeService.getAllEmployees());
        }
        return "employees/listing";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employees/create"; // Return the form with error messages
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employees/edit"; // Return the edit form with validation messages
        }
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/";
    }
}
