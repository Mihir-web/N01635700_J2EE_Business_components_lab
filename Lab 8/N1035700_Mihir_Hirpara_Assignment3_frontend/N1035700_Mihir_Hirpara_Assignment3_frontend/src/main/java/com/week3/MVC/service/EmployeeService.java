package com.week3.MVC.service;

import com.week3.MVC.entity.Employee;
import com.week3.MVC.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID (returning null if not found)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null); // Return null if not found
    }

    // Save or update employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
