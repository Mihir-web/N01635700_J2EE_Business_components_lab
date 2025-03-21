package org.example.employeemanagementsystems.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController{
    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login.html (Thymeleaf template)
    }
}
