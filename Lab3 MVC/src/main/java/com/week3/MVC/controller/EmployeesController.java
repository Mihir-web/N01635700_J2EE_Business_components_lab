package com.week3.MVC.controller;

import com.week3.MVC.entity.Employee;
import com.week3.MVC.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/listing";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Employee employee) {
        employee.setId(id); // Ensure the ID is set
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/";
    }
}