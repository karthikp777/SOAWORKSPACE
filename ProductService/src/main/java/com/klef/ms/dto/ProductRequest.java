package com.klef.ms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductRequest 
{
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Price must be positive")
    private Double price;

    @PositiveOrZero(message = "Quantity should be zero or positive")
    private Integer quantity;
}
