package com.week3.MVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Program code is required.")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Program code must contain only alphanumeric characters.")
    private String programCode;

    @Column(nullable = false)
    @NotBlank(message = "Program name is required.")
    @Size(min = 3, max = 100, message = "Program name must be between 3 and 100 characters.")
    private String programName;

    @Column(nullable = false)
    @NotNull(message = "Fee is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0.")
    private Double durationFee;
}
