package com.week3.MVC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    @Column(name = "user_name", nullable = false, length = 50, unique = true)
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 255, message = "City name cannot exceed 255 characters")
    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^[A-Za-z0-9]{3,10}$", message = "Invalid postal code format")
    @Column(name = "postal_code", nullable = false, length = 255)
    private String postalCode;

    @Column(name = "is_admin", nullable = false)
    private Integer isAdmin = 0;

    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 0;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
