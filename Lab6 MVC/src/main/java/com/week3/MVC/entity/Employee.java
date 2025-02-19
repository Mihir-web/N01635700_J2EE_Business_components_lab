package com.week3.MVC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with all arguments
@AllArgsConstructor // Generates a no-args constructor
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate IDs
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
