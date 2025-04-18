package com.Assignment1_n01635700.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Students {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String city;
    private LocalDate dateOfBirth;

    // Constructor
    public Students(int id, String name, int age, String gender, String email, String city, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}