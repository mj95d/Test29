package com.example.schoolmanagementsoftware.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DtoIn {
    @NotNull
    private Integer quantity;

    @NotNull
    private Integer productId;
}