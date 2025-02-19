package com.week3.MVC.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate = new Date();

    @Column(name = "is_account_non_expired", nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;
}

