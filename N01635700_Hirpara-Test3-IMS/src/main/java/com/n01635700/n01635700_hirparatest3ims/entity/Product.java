package com.n01635700.n01635700_hirparatest3ims.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Product name field is required!")
    private String name;

    @NotBlank(message = "Description field is required!")
    private String description;

    @NotNull(message = "Price field is required!")
    private Long price;

    @NotNull(message = "Stock field is required!")
    private Long stock;

}
