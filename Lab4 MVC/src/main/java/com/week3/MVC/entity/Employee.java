package com.week3.MVC.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Employee {
    private Long id;

    @NotBlank(message = "First Name field is required!")
    @Size(min = 5, max = 20, message = "First Name must be between 5 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Last Name field is required!")
    private String lastName;

    @NotBlank(message = "Email field is required!")
    @Email(message = "Invalid email format!")
    private String email;
}
